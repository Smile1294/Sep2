package filePersistence;

import model.Orders;
import parser.ParserException;
import parser.XmlJsonParser;

import java.io.File;

public class OrdersFile implements OrdersPersistence{
    private XmlJsonParser parser;

    public OrdersFile() {
        this.parser = new XmlJsonParser();
    }

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
