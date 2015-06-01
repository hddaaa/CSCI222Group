package controller.frontController.reservation;

import controller.subSystemFunction.ProfileSystem;
import controller.subSystemFunction.ReservationSystem;
import model.entity.Agent;
import model.entity.Customer;
import model.entity.Ticket;
import model.entity.User;
import util.Enum.UserAuthority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hdd on 15/05/15.
 */
@WebServlet(name = "TicketReservationServlet", urlPatterns = {"/TicketReservation"})
public class TicketReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get user
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //if not login ,then goto register page
        if(session.getAttribute("getTicketInfo")==null){
            session.setAttribute("scheduleId", Integer.parseInt(request.getParameter("scheduleId")));
            session.setAttribute("fareClass", request.getParameter("fareClass"));
            int passageNum = Integer.parseInt(request.getParameter("passageNum"));
            for(int i =0;i<passageNum;i++)
                session.setAttribute("seatNum"+i, Integer.parseInt(request.getParameter("seatNum"+i)));
            session.setAttribute("passageNum",Integer.parseInt(request.getParameter("passageNum")));
            if(request.getParameter("return")!=null){
                session.setAttribute("rscheduleId", Integer.parseInt(request.getParameter("rscheduleId")));
                session.setAttribute("rfareClass", request.getParameter("rfareClass"));
                for(int i =0;i<passageNum;i++)
                    session.setAttribute("rseatNum"+i, Integer.parseInt(request.getParameter("rseatNum"+i)));
            }
            session.setAttribute("getTicketInfo", true);


        }
        if(user==null){

            request.setAttribute("nextStep", "register");
            request.getRequestDispatcher("searchScheduleWithoutLogin.jsp").forward(request, response);

        }else if (session.getAttribute("getPassageEmail")==null) {
            session.setAttribute("getPassageEmail", true);
            if(user.getAuthority()==UserAuthority.Customer) {
                request.setAttribute("MyEmail", user.getUsername());
            }
            request.setAttribute("passageNum",session.getAttribute("passageNum"));
            request.getRequestDispatcher("getPassageEmails.jsp").forward(request, response);

        }else {

            //read post form from session
            int scheduleId = (int) session.getAttribute("scheduleId");
            String fareClass = (String) session.getAttribute("fareClass");
            int passageNum = (int) session.getAttribute("passageNum");
            int[] seatNum = new int[5];
            for (int i =0;i<passageNum;i++){
                seatNum[i] = (int) session.getAttribute("seatNum"+i);
                session.removeAttribute("seatNum"+i);
            }
            session.removeAttribute("getTicketInfo");
            session.removeAttribute("getPassageEmail");
            session.removeAttribute("scheduleId");
            session.removeAttribute("fareClass");
            String agentEmail = null;
            if (user.getAuthority() == UserAuthority.Agent) {
                Agent agent = ProfileSystem.agentDetail(user.getUsername());
                for (int i =0;i<passageNum;i++) {
                    if (!agent.getName().equals(ReservationSystem.customerDetail(request.getParameter("passageEmail"+i)).getTravelAgent())) {
                        request.setAttribute("errorMessage", "one customer not in your Customer List");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                        return;
                    }
                }
                agentEmail = user.getUsername();
            }
            boolean error = false;
            for (int i =0;i<passageNum&&!error;i++) {
                //create new ticket
                Ticket ticket = resTicket(scheduleId,user.getUsername(),seatNum[i],fareClass,agentEmail,request.getParameter("passageEmail"+i));

                //keep ticket in session
                if (ticket != null) {
                    session.setAttribute("ticket" + i, ticket);
                }else {
                    error = true;
                }
            }
            if(session.getAttribute("rscheduleId")!=null){
                int rscheduleId = (int) session.getAttribute("rscheduleId");
                String rfareClass = (String) session.getAttribute("rfareClass");
                int[] rseatNum = new int[5];
                for (int i =0;i<passageNum;i++){
                    rseatNum[i] = (int) session.getAttribute("rseatNum"+i);
                    session.removeAttribute("rseatNum"+i);
                }

                session.removeAttribute("scheduleId");
                session.removeAttribute("fareClass");

                for (int i =0;i<passageNum&&!error;i++) {
                    //create new ticket
                    Ticket ticket = resTicket(rscheduleId,user.getUsername(),rseatNum[i],rfareClass,agentEmail,request.getParameter("passageEmail"+i));

                    //keep ticket in session
                    if (ticket != null) {
                        session.setAttribute("return",true);
                        session.setAttribute("rticket" + i, ticket);
                    }else {
                        error = true;
                    }
                }
            }
            if(!error){
                request.getRequestDispatcher("/SearchServiceForSchedule").forward(request, response);
            }else{
                request.setAttribute("errorMessage", "ticket reservation fail: invalid data");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private Ticket resTicket(int scheduleId,String username,int seatNum,String fareClass,String agentEmail,String passageEmail ){
        Ticket ticket = new Ticket();
        ticket.setUsername(username);
        ticket.setCustomerId(-1);
        ticket.setScheduleId(scheduleId);
        ticket.setFareClass(fareClass);
        ticket.setSeat(seatNum);

        ticket.setFlightCost(ReservationSystem.getPrice(scheduleId, fareClass, agentEmail));
        ticket.setServiceCost(0);
        ticket.setTotal(ticket.getFlightCost());
        ticket = ReservationSystem.ticketReservation(ticket, passageEmail);
        return ticket;
    }

}

