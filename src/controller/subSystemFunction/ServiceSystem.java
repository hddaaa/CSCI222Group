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
    public static List<ServiceInventory> showServiceInventory() {
        try {
            List<ServiceInventory> inventoryList = ServiceInventoryDao.getAllServiceInventory();
            return inventoryList;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addInventory(ServiceInventory serviceInventory) {
        ServiceInventoryDao.addServiceInventory(serviceInventory);
    }

    public static boolean editInventory(ServiceInventory serviceInventory) {
        return ServiceInventoryDao.updateServiceInventory(serviceInventory);
    }

    public static boolean deleteInventory(int serviceInventoryID) {
        return ServiceInventoryDao.delServiceInventory(serviceInventoryID);
    }

}
