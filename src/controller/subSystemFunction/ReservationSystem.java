package controller.subSystemFunction;

import model.dao.*;
import model.entity.*;
import util.common.DataNotFoundException;
import util.common.MathUtil;

import java.util.*;

/**
 * Created by hdd on 15/05/15.
 */
public class ReservationSystem {
    public static boolean register(Customer customer, User user, String pwd) {
        CustomerDao.addCustomer(customer);
        return UserDao.register(user, pwd);
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

    public static Schedule scheduleDetail(int scheduleId) {
        try {
            Schedule schedule =  ScheduleDao.getSchedule(scheduleId);
            return schedule;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Fleet fleetDetail(int fleetId) {
        try {
            Fleet fleet =  FleetDao.getFleet(fleetId);
            return fleet;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Route routeDetail(int routeId) {

        try {

            Route route =  RouteDao.getRoute(routeId);
            return route;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Airport airportDetail(String airportCode){
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
                for (int i =0;i<fleet.getTotal();i++)
                    map.add(0);
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
            if (!agentEmail.isEmpty()) {
                agent = AgentDao.getAgentByEmail(agentEmail);
            }
            Schedule schedule =  ScheduleDao.getSchedule(scheduleId);
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
            if (agentEmail!=null) {
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


    public static Ticket ticketReservation(Ticket ticket) {
        TicketDao.addTicket(ticket);
        try {
            SeatMap seatMap =  SeatMapDao.getSeatMap(ticket.getScheduleId());
            List<Integer> map = seatMap.getMap();
            //check is this customer already reserved
            for (int i : map) {
                if (i == ticket.getCustomerId())
                    return null;
            }
            map.set(ticket.getSeat(), ticket.getCustomerId());
            switch (ticket.getFareClass()){
                case "FClass":
                    seatMap.setfClassSpare(seatMap.getfClassSpare()-1);
                    break;
                case "BClass":
                    seatMap.setbClassSpare(seatMap.getbClassSpare()-1);
                    break;
                case "PEClass":
                    seatMap.setPeClassSpare(seatMap.getPeClassSpare()-1);
                    break;
                case "EClass" :
                    seatMap.seteClassSpare(seatMap.geteClassSpare()-1);
                    break;
                default:
                    System.out.println("Error: ReservationSystem - ticketReservation()");
            }
            SeatMapDao.updateSeatMap(seatMap);
        } catch (DataNotFoundException e) {
            e.printStackTrace();

        }
        return ticket;
    }

    public static List<ServiceInventory> getServiceForSchedule(int scheduleId){
        try {
            Schedule schedule = ScheduleDao.getSchedule(scheduleId);
            Route route = RouteDao.getRoute(schedule.getRoute());
            Airport sourceAirport = AirportDao.getAirport(route.getSourceAirport());
            Airport destinationAirport = AirportDao.getAirport(route.getDestinationAirport());
            if(sourceAirport.getCountry().equals(destinationAirport.getCountry())){
                return ServiceInventoryDao.getServicesByAvailable("all");
            }else {
                return ServiceInventoryDao.getAllServiceInventory();
            }
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Ticket serviceReservation(String[] serviceAndCostRow,Ticket ticket){
        for(String serviceAndCost : serviceAndCostRow){
            Service service = new Service();
            String[] str = serviceAndCost.split(",");
            service.setItem(str[0]);
            service.setCost(Integer.parseInt(str[1]));
            service.setTicketId(ticket.getId());
            service.setCustomerId(ticket.getCustomerId());
            ServiceDao.addService(service);
            ticket.setServiceCost(ticket.getServiceCost() + Integer.parseInt(str[1]));
            ticket.setTotal(ticket.getFlightCost()+ticket.getServiceCost());
        }

        if (TicketDao.updateTicket(ticket)==true)
        return ticket;
        else
            return null;
    }

    public static List<Integer> getSeatMap(int scheduleId){
        try {
            SeatMap seatMap = SeatMapDao.getSeatMap(scheduleId);
            return seatMap.getMap();
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Ticket changeSeat(Ticket ticket , int seatNum){
        try {
            SeatMap seatMap =  SeatMapDao.getSeatMap(ticket.getScheduleId());
            List<Integer> map = seatMap.getMap();
            map.set(seatNum,ticket.getCustomerId());
            map.set(ticket.getSeat(), 0);
            seatMap.setMap(map);
            ticket.setSeat(seatNum);
            SeatMapDao.updateSeatMap(seatMap);
            return ticket;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Ticket switchSeat(Ticket thisTicket , int seatNum){
        try {
            SeatMap seatMap = SeatMapDao.getSeatMap(thisTicket.getScheduleId());
            List<Integer> map = seatMap.getMap();
            Ticket otherTicket = TicketDao.getTicket(map.get(seatNum), thisTicket.getScheduleId());
            map.set(seatNum, thisTicket.getCustomerId());
            map.set(thisTicket.getSeat(), otherTicket.getCustomerId());
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

    public static List<Service> getServiceList(int ticketId){
        try {
            return ServiceDao.getServicesInATicket(ticketId);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean deleteServiceReservation(int serviceId){
        return ServiceDao.delService(serviceId);
    }
    public static Ticket changeFlight(Ticket oldTicket,Ticket newTicket){

        try {
            SeatMap oldSeatMap = SeatMapDao.getSeatMap(oldTicket.getScheduleId());
            SeatMap newSeatMap = SeatMapDao.getSeatMap(newTicket.getScheduleId());
            List<Integer> oldMap = oldSeatMap.getMap();
            List<Integer> newMap = newSeatMap.getMap();
            oldMap.set(oldTicket.getSeat(),0);
            newMap.set(newTicket.getSeat(), newTicket.getCustomerId());
            oldSeatMap.setMap(oldMap);
            newSeatMap.setMap(newMap);
            SeatMapDao.updateSeatMap(oldSeatMap);
            SeatMapDao.updateSeatMap(newSeatMap);
            List<Service> serviceList = ServiceDao.getServicesInATicket(oldTicket.getId());
            for(Service service : serviceList){
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
}

