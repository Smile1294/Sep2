package filePersistence;

import model.Orders;

public interface OrdersPersistence {
    void save(Orders ordersList, String filename);
    Orders load(String fileName);
}
