package controller.subSystemFunction;

import model.dao.*;
import model.entity.*;
import util.Enum.UserAuthority;
import util.common.DataNotFoundException;
import util.common.MathUtil;
import util.common.ParseDateUtil;

import java.util.*;

/**
 * Created by hdd on 15/05/15.
 */
public class ReservationSystem {
    public static boolean register(Customer customer, User user, String pwd) {
        return CustomerDao.addCustomer(customer) && UserDao.register(user, pwd);
    }

    public static User login(String username, String pwd) {
        return UserDao.login(username, pwd);
    }

    public static List searchSchedule(String sourceAirport, String destinationAirport, Date departureDate) {
        try {
            Route route = RouteDao.getRoute(sourceAirport, destinationAirport);
            List<Schedule> scheduleList = ScheduleDao.getScheduleInRoute(route.getId());
            Calendar departureCal = Calendar.getInstance();
            Calendar scheduleCal = Calendar.getInstance();
            departureCal.setTime(departureDate);
            List<Schedule> foundList = new ArrayList<>();
            for (Schedule schedule : scheduleList) {

                scheduleCal.setTime(schedule.getDepartTime());
                if (departureCal.get(Calendar.MONTH) == scheduleCal.get(Calendar.MONTH) && departureCal.get(Calendar.DATE) == scheduleCal.get(Calendar.DATE)) {
                    foundList.add(schedule);
                }
            }
            if (foundList.isEmpty())
                return scheduleList;
            else
                return foundList;
        } catch (DataNotFoundException e) {
            return null;
        }

    }
    public static List searchScheduleForModify(String sourceAirport, String destinationAirport) {
        try {
            Route route = RouteDao.getRoute(sourceAirport, destinationAirport);
            List<Schedule> scheduleList = ScheduleDao.getScheduleInRoute(route.getId());
            return scheduleList;

        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
    public static Ticket ticketDetail(int ticketId) {
        try {
            Ticket ticket = TicketDao.getTicket(ticketId);
            return ticket;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Customer customerDetail(int customerId){
        try {
            return CustomerDao.getCustomer(customerId);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Customer customerDetail(String customerEmail){
        try {
            return CustomerDao.getCustomerByEmail(customerEmail);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Schedule scheduleDetail(int scheduleId) {
        try {
            Schedule schedule = ScheduleDao.getSchedule(scheduleId);
            return schedule;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Fleet fleetDetail(int fleetId) {
        try {
            Fleet fleet = FleetDao.getFleet(fleetId);
            return fleet;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Route routeDetail(int routeId) {

        try {

            Route route = RouteDao.getRoute(routeId);
            return route;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Airport airportDetail(String airportCode) {
        try {
            return AirportDao.getAirport(airportCode);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SeatMap showSeatMap(int scheduleId) {
        try {
            SeatMap seatMap = SeatMapDao.getSeatMap(scheduleId);
            return seatMap;
        } catch (DataNotFoundException e) {
            try {
                SeatMap seatMap = new SeatMap();
                Schedule schedule = ScheduleDao.getSchedule(scheduleId);
                Fleet fleet = FleetDao.getFleet(schedule.getPlane());
                seatMap.setfClassSpare(fleet.getFClass());
                seatMap.setbClassSpare(fleet.getBClass());
                seatMap.setPeClassSpare(fleet.getPEClass());
                seatMap.seteClassSpare(fleet.getEClass());
                seatMap.setScheduleId(scheduleId);
                List map = new ArrayList();
                for (int i = 0; i < fleet.getTotal(); i++)
                    map.add((double) -1);
                seatMap.setMap(map);
                SeatMapDao.addSeatMap(seatMap);
                return seatMap;
            } catch (DataNotFoundException e1) {
                e1.printStackTrace();
                return null;
            }

        }
    }

    public static int getPrice(int scheduleId, String fareClass, String agentEmail) {

        try {
            Agent agent = null;
            if (agentEmail != null && !agentEmail.isEmpty()) {
                agent = AgentDao.getAgentByEmail(agentEmail);
            }
            Schedule schedule = ScheduleDao.getSchedule(scheduleId);
            Route route = RouteDao.getRoute(schedule.getRoute());
            Airport sourceAirport = AirportDao.getAirport(route.getSourceAirport());
            Airport destinationPort = AirportDao.getAirport(route.getDestinationAirport());
            double lat1 = Float.parseFloat(sourceAirport.getLatitude());
            double lon1 = Float.parseFloat(sourceAirport.getLongitude());
            double lat2 = Float.parseFloat(destinationPort.getLatitude());
            double lon2 = Float.parseFloat(destinationPort.getLongitude());

            double distance = MathUtil.getDistance(lat1, lon1, lat2, lon2);

            int price = (int) Math.rint(MathUtil.PRICE_PER_KM * distance * MathUtil.getPriceFloat(fareClass, agent == null ? "" : agent.getName()));

            return price;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Map<String, Integer> getPriceList(Route route, String agentEmail) {
        try {
            Agent agent = null;
            if (agentEmail != null) {
                agent = AgentDao.getAgentByEmail(agentEmail);
            }
            Airport sourceAirport = AirportDao.getAirport(route.getSourceAirport());
            Airport destinationPort = AirportDao.getAirport(route.getDestinationAirport());
            double lat1 = Float.parseFloat(sourceAirport.getLatitude());
            double lon1 = Float.parseFloat(sourceAirport.getLongitude());
            double lat2 = Float.parseFloat(destinationPort.getLatitude());
            double lon2 = Float.parseFloat(destinationPort.getLongitude());

            double distance = MathUtil.getDistance(lat1, lon1, lat2, lon2);

            Map<String, Integer> priceMap = new HashMap<String, Integer>();
            priceMap.put("FClass", (int) Math.rint(MathUtil.PRICE_PER_KM * distance * MathUtil.getPriceFloat("F", agent == null ? null : agent.getName())));
            priceMap.put("BClass", (int) Math.rint(MathUtil.PRICE_PER_KM * distance * MathUtil.getPriceFloat("B", agent == null ? null : agent.getName())));
            priceMap.put("PEClass", (int) Math.rint(MathUtil.PRICE_PER_KM * distance * MathUtil.getPriceFloat("PE", agent == null ? null : agent.getName())));
            ;
            priceMap.put("EClass", (int) Math.rint(MathUtil.PRICE_PER_KM * distance * MathUtil.getPriceFloat("E", agent == null ? null : agent.getName())));
            return priceMap;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Ticket ticketReservation(Ticket ticket, String customerEmail) {
        try {
            Customer customer;
            if (customerEmail != null) {
                customer = CustomerDao.getCustomerByEmail(customerEmail);
            } else {
                customer = CustomerDao.getCustomerByEmail(ticket.getUsername());
            }
            ticket.setCustomerId(customer.getId());
            SeatMap seatMap = SeatMapDao.getSeatMap(ticket.getScheduleId());
            List map = seatMap.getMap();
            //check is this customer already reserved
            for (int i = 0; i < map.size(); i++) {
                if ((double) map.get(i) == ticket.getCustomerId())
                    return null;
            }
            TicketDao.addTicket(ticket);
            map.set(ticket.getSeat(), ticket.getCustomerId());
            switch (ticket.getFareClass()) {
                case "FClass":
                    seatMap.setfClassSpare(seatMap.getfClassSpare() - 1);
                    break;
                case "BClass":
                    seatMap.setbClassSpare(seatMap.getbClassSpare() - 1);
                    break;
                case "PEClass":
                    seatMap.setPeClassSpare(seatMap.getPeClassSpare() - 1);
                    break;
                case "EClass":
                    seatMap.seteClassSpare(seatMap.geteClassSpare() - 1);
                    break;
                default:
                    System.out.println("Error: ReservationSystem - ticketReservation()");
            }
            SeatMapDao.updateSeatMap(seatMap);
            return TicketDao.getTicket(ticket.getCustomerId(), ticket.getScheduleId());
        } catch (DataNotFoundException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static List<ServiceInventory> getServiceForSchedule(int scheduleId) {
        try {
            Schedule schedule = ScheduleDao.getSchedule(scheduleId);
            Route route = RouteDao.getRoute(schedule.getRoute());
            Airport sourceAirport = AirportDao.getAirport(route.getSourceAirport());
            Airport destinationAirport = AirportDao.getAirport(route.getDestinationAirport());
            if (sourceAirport.getCountry().equals(destinationAirport.getCountry())) {
                return ServiceInventoryDao.getServicesByAvailable("all");
            } else {
                return ServiceInventoryDao.getAllServiceInventory();
            }
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Ticket serviceReservation(String[] serviceAndCostRow, Ticket ticket) {
        for (String serviceAndCost : serviceAndCostRow) {
            Service service = new Service();
            String[] str = serviceAndCost.split(",");
            service.setItem(str[0]);
            service.setCost(Integer.parseInt(str[1]));
            service.setTicketId(ticket.getId());
            service.setCustomerId(ticket.getCustomerId());
            ServiceDao.addService(service);
            ticket.setServiceCost(ticket.getServiceCost() + Integer.parseInt(str[1]));
            ticket.setTotal(ticket.getFlightCost() + ticket.getServiceCost());
        }

        if (TicketDao.updateTicket(ticket) == true)
            return ticket;
        else
            return null;
    }

    public static List<Map<String, String>> getAllBookingsFromACustomer(String customerEmail) {
        try {
            Customer customer = CustomerDao.getCustomerByEmail(customerEmail);
            List<Ticket> ticketList = TicketDao.getAllTicketFormACustomer(customer.getId());
            List<Map<String, String>> bookingsList = new ArrayList<>();
            for (Ticket ticket : ticketList) {
                Map<String, String> bookingMap = new HashMap<>();
                bookingMap.put("ticketId", "" + ticket.getId());
                bookingMap.put("totalCost", "" + ticket.getTotal());
                bookingMap.put("serviceCost", "" + ticket.getServiceCost());
                bookingMap.put("flightCost", "" + ticket.getFlightCost());
                bookingMap.put("fareClass", "" + ticket.getFareClass());
                bookingMap.put("seat", "" + (ticket.getSeat()+1));
                Schedule schedule = ScheduleDao.getSchedule(ticket.getScheduleId());
                Route route = RouteDao.getRoute(schedule.getRoute());
                Airport sourceAirport = AirportDao.getAirport(route.getSourceAirport());
                Airport destinationAirport = AirportDao.getAirport(route.getDestinationAirport());
                bookingMap.put("sourceAirport", sourceAirport.getCity());
                bookingMap.put("destinationAirport", destinationAirport.getCity());
                bookingMap.put("departTime", ParseDateUtil.formatDate(schedule.getDepartTime(), sourceAirport.getDatabase_timezone()));
                bookingMap.put("arriveTime", ParseDateUtil.formatDate(schedule.getArrivedTime(), destinationAirport.getDatabase_timezone()));
                bookingsList.add(bookingMap);
            }
            if (bookingsList.size() > 0)
                return bookingsList;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> getBookingDetail(int ticketId) {
        try {
            Ticket ticket = TicketDao.getTicket(ticketId);
            Customer customer = CustomerDao.getCustomer(ticket.getCustomerId());
            Map<String, String> bookingMap = new HashMap<>();
            bookingMap.put("totalCost", "" + ticket.getTotal());
            bookingMap.put("serviceCost", "" + ticket.getServiceCost());
            bookingMap.put("flightCost", "" + ticket.getFlightCost());
            bookingMap.put("fareClass", "" + ticket.getFareClass());
            bookingMap.put("seat", "" + (ticket.getSeat()+1));
            bookingMap.put("customer", customer.getFirstName() + " " + customer.getLastName());
            bookingMap.put("email", customer.getEmail());
            Schedule schedule = ScheduleDao.getSchedule(ticket.getScheduleId());
            Route route = RouteDao.getRoute(schedule.getRoute());
            Airport sourceAirport = AirportDao.getAirport(route.getSourceAirport());
            Airport destinationAirport = AirportDao.getAirport(route.getDestinationAirport());
            bookingMap.put("sourceAirport", sourceAirport.getCity());
            bookingMap.put("destinationAirport", destinationAirport.getCity());
            bookingMap.put("departTime", ParseDateUtil.formatDate(schedule.getDepartTime(), sourceAirport.getDatabase_timezone()));
            bookingMap.put("arriveTime", ParseDateUtil.formatDate(schedule.getArrivedTime(), destinationAirport.getDatabase_timezone()));
            if (bookingMap.size() > 0)
                return bookingMap;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Integer> getSeatMap(int scheduleId) {
        try {
            SeatMap seatMap = SeatMapDao.getSeatMap(scheduleId);
            return seatMap.getMap();
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Ticket changeSeat(Ticket ticket, int seatNum) {
        try {
            SeatMap seatMap = SeatMapDao.getSeatMap(ticket.getScheduleId());
            List map = seatMap.getMap();
            map.set(seatNum, ticket.getCustomerId());
            map.set(ticket.getSeat(), (double)-1);
            seatMap.setMap(map);
            ticket.setSeat(seatNum);
            if(SeatMapDao.updateSeatMap(seatMap)&&TicketDao.updateTicket(ticket))
                return ticket;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Ticket switchSeat(Ticket thisTicket, int seatNum) {
        try {
            SeatMap seatMap = SeatMapDao.getSeatMap(thisTicket.getScheduleId());
            List map = seatMap.getMap();
            Ticket otherTicket = TicketDao.getTicket((int)(double)map.get(seatNum), thisTicket.getScheduleId());
            map.set(seatNum, (double)thisTicket.getCustomerId());
            map.set(thisTicket.getSeat(), (double)otherTicket.getCustomerId());
            seatMap.setMap(map);
            SeatMapDao.updateSeatMap(seatMap);
            otherTicket.setSeat(thisTicket.getSeat());
            thisTicket.setSeat(seatNum);
            TicketDao.updateTicket(thisTicket);
            TicketDao.updateTicket(otherTicket);
            return thisTicket;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Service> getServiceList(int ticketId) {
        try {
            return ServiceDao.getServicesInATicket(ticketId);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteServiceReservation(int serviceId) {
        try {
            Service service = ServiceDao.getService(serviceId);
            Ticket ticket = TicketDao.getTicket(service.getTicketId());
            ticket.setServiceCost(ticket.getServiceCost() - service.getCost());
            ticket.setTotal(ticket.getServiceCost() + ticket.getFlightCost());
            return TicketDao.updateTicket(ticket)&&ServiceDao.delService(serviceId);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Ticket changeFlight(Ticket oldTicket, Ticket newTicket) {

        try {
            SeatMap oldSeatMap = SeatMapDao.getSeatMap(oldTicket.getScheduleId());
            SeatMap newSeatMap = SeatMapDao.getSeatMap(newTicket.getScheduleId());
            List<Integer> oldMap = oldSeatMap.getMap();
            List<Integer> newMap = newSeatMap.getMap();
            oldMap.set(oldTicket.getSeat(), 0);
            newMap.set(newTicket.getSeat(), newTicket.getCustomerId());
            oldSeatMap.setMap(oldMap);
            newSeatMap.setMap(newMap);
            SeatMapDao.updateSeatMap(oldSeatMap);
            SeatMapDao.updateSeatMap(newSeatMap);
            List<Service> serviceList = ServiceDao.getServicesInATicket(oldTicket.getId());
            for (Service service : serviceList) {
                service.setTicketId(newTicket.getId());
                ServiceDao.updateService(service);
            }
            TicketDao.delTicket(oldTicket.getId());
            TicketDao.addTicket(newTicket);
            return newTicket;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ServiceInventory> getServiceNotInTicket(int ticketId){
        Ticket ticket = ReservationSystem.ticketDetail(ticketId);
        List<ServiceInventory> allServices = ReservationSystem.getServiceForSchedule(ticket.getScheduleId());
        List<Service> orderedServices = ReservationSystem.getServiceList(ticketId);
        List<ServiceInventory> findServices =new ArrayList<>();
        boolean flag = false;
        for(ServiceInventory si : allServices){
            flag = false;
            for(Service s : orderedServices){
                if (s.getItem().equals(si.getItem())){
                    flag = true;
                    break;
                }
            }
            if (!flag)
                findServices.add(si);
        }
        if (findServices.isEmpty())
            return null;
        return findServices;
    }

    public static boolean changeUsernameByStaff(User user, String oldUsername,String newUsername){
        if(user.getAuthority().compareTo(UserAuthority.Staff)>=0){
            return UserDao.changeUsername(oldUsername, newUsername);
        }else
            return false;
    }
    public static String addSchedule(int fleetId,String sAirport,String dAirport,Date dTime,Date aTime){
        Route route=null;
        try {
            route = RouteDao.getRoute(sAirport,dAirport);

        } catch (DataNotFoundException e) {
            Route newRoute = new Route(sAirport,dAirport,false,0);
            RouteDao.addRoute(newRoute);
            try {
                route = RouteDao.getRoute(sAirport,dAirport);
            } catch (DataNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        try {
            List<Schedule> scheduleList = ScheduleDao.getAllSchedule();
            Random random = new Random();
            boolean flag = false;
            String flightId =null;
            while (!flag){
                flag=true;
                flightId ="QF"+(random.nextInt(9000)+1000);
                for(Schedule schedule: scheduleList){
                    if(schedule.getFlightID().equals(flightId)){
                        flag=false;
                        break;
                    }
                }
            }
            Schedule schedule = new Schedule(flightId,fleetId,route.getId(),dTime,aTime);
            ScheduleDao.addSchedule(schedule);
            return flightId;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean modifySchedule(int scheduleId, int fleetId,String sAirport,String dAirport,Date dTime,Date aTime){
        Route route=null;
        try {
            route = RouteDao.getRoute(sAirport,dAirport);

        } catch (DataNotFoundException e) {
            Route newRoute = new Route(sAirport,dAirport,false,0);
            RouteDao.addRoute(newRoute);
            try {
                route = RouteDao.getRoute(sAirport,dAirport);
            } catch (DataNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        try {
            Schedule schedule = ScheduleDao.getSchedule(scheduleId);
            schedule.setPlane(fleetId);
            schedule.setRoute(route.getId());
            schedule.setDepartTime(dTime);
            schedule.setArrivedTime(aTime);
            return ScheduleDao.updateSchedule(schedule);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}

