package controller.subSystemFunction;

import model.dao.ServiceInventoryDao;
import model.entity.Entity;
import model.entity.ServiceInventory;
import util.common.DataNotFoundException;

import java.util.List;

/**
 * Created by hdd on 15/05/15.
 */
public class ServiceSystem {
    public static List<Entity> showServiceInventory() {
        ServiceInventoryDao serviceInventoryDao = new ServiceInventoryDao();
        try {
            List<Entity> inventoryList = serviceInventoryDao.getAllEntity();
            return inventoryList;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addInventory(ServiceInventory serviceInventory) {
        ServiceInventoryDao serviceInventoryDao = new ServiceInventoryDao();
        serviceInventoryDao.addEntity(serviceInventory);
    }

    public static boolean editInventory(ServiceInventory serviceInventory) {
        ServiceInventoryDao serviceInventoryDao = new ServiceInventoryDao();
        return serviceInventoryDao.updateEntity(serviceInventory);
    }

    public static boolean deleteInventory(int serviceInventoryID) {
        ServiceInventoryDao serviceInventoryDao = new ServiceInventoryDao();
        return serviceInventoryDao.delEntity(serviceInventoryID);
    }

}
