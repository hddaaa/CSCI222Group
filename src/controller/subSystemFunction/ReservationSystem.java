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
        CustomerDao customerDao = new CustomerDao();
        customerDao.addEntity(customer);
        UserDao userDao = new UserDao();
        return userDao.register(user, pwd);
    }

    public static List searchSchedule(String sourceAirport, String destinationAirport, Date departureDate) {
        try {
            AirportDao airportDao = new AirportDao();
            Airport sAirport = airportDao.getAirport(sourceAirport);
            Airport dAirport = airportDao.getAirport(destinationAirport);

            RouteDao routeDao = new RouteDao();
            Route route = routeDao.getRoute(sAirport.getIATA_FAA(), dAirport.getIATA_FAA());
            ScheduleDao scheduleDao = new ScheduleDao();
            List<Schedule> scheduleList = scheduleDao.getScheduleInRoute(route.getId());
            Calendar departureCal = Calendar.getInstance();
            Calendar scheduleCal = Calendar.getInstance();
            departureCal.setTime(departureDate);
            List<Schedule> foundList = new ArrayList<>();
            for (Schedule schedule : scheduleList) {

                scheduleCal.setTime(schedule.getDepartTime());
                if (departureCal.get(Calendar.MONTH) == scheduleCal.get(Calendar.MONTH) && departureCal.get(Calendar.DAY_OF_MONTH) == scheduleCal.get(Calendar.DAY_OF_MONTH)) {
                    foundList.add(schedule);
                }
            }
            if (foundList.isEmpty())
                return null;
            else
                return foundList;
        } catch (DataNotFoundException e) {
            return null;
        }

    }

    public static Schedule scheduleDetail(int scheduleId) {
        try {
            ScheduleDao scheduleDao = new ScheduleDao();
            Schedule schedule = (Schedule) scheduleDao.getEntity(scheduleId);
            return schedule;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Fleet fleetDetail(int fleetId) {
        try {
            FleetDao fleetDao = new FleetDao();
            Fleet fleet = (Fleet) fleetDao.getEntity(fleetId);
            return fleet;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Route routeDetail(int routeId) {

        try {
            RouteDao routeDao = new RouteDao();
            Route route = (Route) routeDao.getEntity(routeId);
            return route;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Integer> showEmptySeat(int scheduleId) {
        try {
            SeatMapDao seatMapDao = new SeatMapDao();
            SeatMap seatMap = (SeatMap) seatMapDao.getEntity(scheduleId);
            return seatMap.getMap();
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getPrice(int scheduleId, String fareClass, String agentEmail) {
        ScheduleDao scheduleDao = new ScheduleDao();
        RouteDao routeDao = new RouteDao();
        AirportDao airportDao = new AirportDao();
        AgentDao agentDao = new AgentDao();
        try {
            Agent agent = null;
            if (!agentEmail.isEmpty()) {
                agent = agentDao.getAgentByEmail(agentEmail);
            }
            Schedule schedule = (Schedule) scheduleDao.getEntity(scheduleId);
            Route route = (Route) routeDao.getEntity(schedule.getRoute());
            Airport sourceAirport = airportDao.getAirport(route.getSourceAirport());
            Airport destinationPort = airportDao.getAirport(route.getDestinationAirport());
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
        AirportDao airportDao = new AirportDao();
        AgentDao agentDao = new AgentDao();
        try {
            Agent agent = null;
            if (!agentEmail.isEmpty()) {
                agent = agentDao.getAgentByEmail(agentEmail);
            }
            Airport sourceAirport = airportDao.getAirport(route.getSourceAirport());
            Airport destinationPort = airportDao.getAirport(route.getDestinationAirport());
            double lat1 = Float.parseFloat(sourceAirport.getLatitude());
            double lon1 = Float.parseFloat(sourceAirport.getLongitude());
            double lat2 = Float.parseFloat(destinationPort.getLatitude());
            double lon2 = Float.parseFloat(destinationPort.getLongitude());

            double distance = MathUtil.getDistance(lat1, lon1, lat2, lon2);

            Map<String, Integer> priceMap = new HashMap<String, Integer>();
            priceMap.put("FClass", (int) Math.rint(MathUtil.PRICE_PER_KM * distance * MathUtil.getPriceFloat("F", agent == null ? "" : agent.getName())));
            priceMap.put("BClass", (int) Math.rint(MathUtil.PRICE_PER_KM * distance * MathUtil.getPriceFloat("B", agent == null ? "" : agent.getName())));
            priceMap.put("PEClass", (int) Math.rint(MathUtil.PRICE_PER_KM * distance * MathUtil.getPriceFloat("PE", agent == null ? "" : agent.getName())));
            ;
            priceMap.put("EClass", (int) Math.rint(MathUtil.PRICE_PER_KM * distance * MathUtil.getPriceFloat("E", agent == null ? "" : agent.getName())));
            return priceMap;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Ticket ticketReservation(Ticket ticket) {
        TicketDao ticketDao = new TicketDao();
        ticketDao.addEntity(ticket);
        SeatMapDao seatMapDao = new SeatMapDao();
        try {
            SeatMap seatMap = (SeatMap) seatMapDao.getEntity(ticket.getScheduleId());
            List<Integer> map = seatMap.getMap();
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
            seatMapDao.updateEntity(seatMap);
        } catch (DataNotFoundException e) {
            ScheduleDao scheduleDao = new ScheduleDao();
            FleetDao fleetDao = new FleetDao();
            SeatMap seatMap = new SeatMap();
            try {
                Schedule schedule = (Schedule) scheduleDao.getEntity(ticket.getScheduleId());
                Fleet fleet = (Fleet) fleetDao.getEntity(schedule.getPlane());
                seatMap.setfClassSpare(fleet.getFClass());
                seatMap.setbClassSpare(fleet.getBClass());
                seatMap.setPeClassSpare(fleet.getPEClass());
                seatMap.seteClassSpare(fleet.getEClass());
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
            } catch (DataNotFoundException e1) {
                e1.printStackTrace();
            }
            seatMap.setScheduleId(ticket.getScheduleId());
            List map = new ArrayList();
            map.set(ticket.getSeat(), ticket.getCustomerId());
            seatMap.setMap(map);
            seatMapDao.addEntity(seatMap);
        }
        return ticket;
    }

    public static List<ServiceInventory> getServiceForSchedule(int scheduleId){
        ScheduleDao scheduleDao = new ScheduleDao();
        RouteDao routeDao = new RouteDao();
        AirportDao airportDao = new AirportDao();
        ServiceInventoryDao serviceInventoryDao = new ServiceInventoryDao();
        try {
            Schedule schedule = (Schedule) scheduleDao.getEntity(scheduleId);
            Route route = (Route) routeDao.getEntity(schedule.getRoute());
            Airport sourceAirport = airportDao.getAirport(route.getSourceAirport());
            Airport destinationAirport = airportDao.getAirport(route.getDestinationAirport());
            if(sourceAirport.getCountry().equals(destinationAirport.getCountry())){
                return serviceInventoryDao.getServicesByAvailable("all");
            }else {
                List<Entity> entityList =serviceInventoryDao.getAllEntity();
                List<ServiceInventory> serviceInventories=new ArrayList<ServiceInventory>();
                for(Entity entity : entityList){
                    serviceInventories.add((ServiceInventory)entity);
                }
                return serviceInventories;
            }
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Ticket serviceReservation(String[] serviceAndCostRow,Ticket ticket){
        ServiceDao serviceDao = new ServiceDao();
        TicketDao ticketDao = new TicketDao();


        for(String serviceAndCost : serviceAndCostRow){
            Service service = new Service();
            String[] str = serviceAndCost.split(",");
            service.setItem(str[0]);
            service.setCost(Integer.parseInt(str[1]));
            service.setTicketId(ticket.getId());
            service.setCustomerId(ticket.getCustomerId());
            serviceDao.addEntity(service);
            ticket.setServiceCost(ticket.getServiceCost() + Integer.parseInt(str[1]));
            ticket.setTotal(ticket.getFlightCost()+ticket.getServiceCost());
        }

        if (ticketDao.updateEntity(ticket)==true)
        return ticket;
        else
            return null;
    }

    public static List<Integer> getSeatMap(int scheduleId){
        SeatMapDao seatMapDao = new SeatMapDao();
        try {
            SeatMap seatMap = (SeatMap) seatMapDao.getEntity(scheduleId);
            return seatMap.getMap();
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Ticket changeSeat(Ticket ticket , int seatNum){
        SeatMapDao seatMapDao = new SeatMapDao();
        try {
            SeatMap seatMap = (SeatMap) seatMapDao.getEntity(ticket.getScheduleId());
            List<Integer> map = seatMap.getMap();
            map.set(seatNum,ticket.getCustomerId());
            map.set(ticket.getSeat(), 0);
            ticket.setSeat(seatNum);
            return ticket;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Ticket switchSeat(Ticket thisTicket , int seatNum){
        SeatMapDao seatMapDao = new SeatMapDao();
        TicketDao ticketDao = new TicketDao();
        try {
            SeatMap seatMap = (SeatMap) seatMapDao.getEntity(thisTicket.getScheduleId());
            List<Integer> map = seatMap.getMap();
            Ticket otherTicket = ticketDao.getTicket(map.get(seatNum), thisTicket.getScheduleId());
            map.set(seatNum, thisTicket.getCustomerId());
            map.set(thisTicket.getSeat(), otherTicket.getCustomerId());
            seatMap.setMap(map);
            seatMapDao.updateEntity(seatMap);
            otherTicket.setSeat(thisTicket.getSeat());
            thisTicket.setSeat(seatNum);
            ticketDao.updateEntity(thisTicket);
            ticketDao.updateEntity(otherTicket);
            return thisTicket;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Service> getServiceList(int ticketId){
        ServiceDao serviceDao= new ServiceDao();
        try {
            return serviceDao.getServicesInATicket(ticketId);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean deleteServiceReservation(int serviceId){
        ServiceDao serviceDao = new ServiceDao();
        return serviceDao.delEntity(serviceId);
    }
    public static Ticket changeFlight(Ticket oldTicket,Ticket newTicket){
        SeatMapDao seatMapDao = new SeatMapDao();
        TicketDao ticketDao = new TicketDao();
        ServiceDao serviceDao = new ServiceDao();
        try {
            SeatMap oldSeatMap = (SeatMap) seatMapDao.getEntity(oldTicket.getScheduleId());
            SeatMap newSeatMap = (SeatMap) seatMapDao.getEntity(newTicket.getScheduleId());
            List<Integer> oldMap = oldSeatMap.getMap();
            List<Integer> newMap = newSeatMap.getMap();
            oldMap.set(oldTicket.getSeat(),0);
            newMap.set(newTicket.getSeat(), newTicket.getCustomerId());
            oldSeatMap.setMap(oldMap);
            newSeatMap.setMap(newMap);
            seatMapDao.updateEntity(oldSeatMap);
            seatMapDao.updateEntity(newSeatMap);
            List<Service> serviceList = serviceDao.getServicesInATicket(oldTicket.getId());
            for(Service service : serviceList){
                service.setTicketId(newTicket.getId());
                serviceDao.updateEntity(service);
            }
            ticketDao.delEntity(oldTicket.getId());
            ticketDao.addEntity(newTicket);
            return newTicket;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

