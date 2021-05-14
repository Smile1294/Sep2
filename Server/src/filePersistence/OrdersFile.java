package filePersistence;

import model.Orders;
import parser.ParserException;
import parser.XmlJsonParser;

import java.io.File;

/**
 * OrdersFiles class is implementing OrdersPersistence for saving and loading orders
 */

public class OrdersFile implements OrdersPersistence{
    private XmlJsonParser parser;

    /**
     * Constructor initialising instance variable
     */


    public OrdersFile() {
        this.parser = new XmlJsonParser();
    }

    /**
     * saving orders list into a file
     * @param ordersList list of orders that is being stored
     * @param filename name of a file that is information stored on
     */

    @Override
    public void save(Orders ordersList, String filename) {
        File file = null;
        try {
            file = parser.toJson(ordersList, filename);
        } catch (ParserException e){
            System.err.println(e.getMessage());
        }
        System.out.println("Saved to file: " + file.getAbsolutePath());
    }

    /**
     * loading orders list from a file
     * @param fileName from file to load
     * @return order list
     */

    @Override
    public Orders load(String fileName) {
        Orders userList = new Orders();
        try {
            userList = parser.fromJson(fileName, Orders.class);
        } catch (ParserException e){
            System.err.println(e.getMessage());
        }
        System.out.println("Loaded from file: " + fileName);
        return userList;
    }
}
