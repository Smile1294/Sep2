package model;

import filePersistence.UserListPersistence;
import persistence.*;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManger implements Model {
    private Orders orders;
    private Companies companies;
    private UserList userList;
    private Stocks stocks;
    private UserListPersistence userListPersistence;
    private UsersPersistence usersPersistence;
    private CompaniesPersistence companiesPersistence;
    private OrdersPersistence ordersPersistence;
    private StocksPersistence stocksPersistence;

    public ModelManger() throws IOException, SQLException {

        usersPersistence = UsersDatabase.getInstance();
        companiesPersistence = CompaniesDatabase.getInstance();
        ordersPersistence = OrdersDatabase.getInstance();
        stocksPersistence = StocksDatabase.getInstance();
        userList = usersPersistence.load();
        companies = companiesPersistence.load();
        orders = ordersPersistence.load();

        for (User u : userList.getUsers()) {
            for (Company c : companies.getCompanies()) {
                u.addStock(stocksPersistence.load(u, c));
            }
        }

    }

    public User getUser(String name) {
        return userList.getUser(new UserName(name));
    }

    public ArrayList<Stock> LoaduserStocks(String name) {
        ArrayList<Stock> temporaryList = new ArrayList<Stock>();
        try {
            for (Company s : getAllCompanies()) {
                getUser(name).getStocks().getStockBySymbol(s.getSymbol()).setAmount(orders.getCompeltedUserOwnedStock(s.getSymbol(), name));
                temporaryList.add(getUser(name).getStocks().getStockBySymbol(s.getSymbol()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temporaryList;
    }

    public ArrayList<Order> getOrders() {
        return orders.getOrders();
    }

    public Orders getPortfolioOrders(User user) {
        return orders.getOrderByUser(user);
    }

    public Double getPriceTotal(String name) {
        double d = 0.0;
        try {
            for (Company s : companies.getCompanies()) {
                d = d + s.getCurrentPrice() * getUser(name).getStocks().getStockBySymbol(s.getSymbol()).getAmount();
            }
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }


    public void AddOrder(Order order) {
        if (order.isSell()) {
            if (getUser(order.getUser()).getStocks().getStockBySymbol(order.getSymbol()).getAmount() > order.getAmount()) {
                orders.AddOrder(order);
                try {
                    new Thread(orders).start();
                    ordersPersistence.save(order);
                    ordersPersistence.update(orders);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println("Added order for sale");
            } else {
                System.out.println("Insufficient resources");
            }
        } else {
            if (getUser(order.getUser()).getBalance() > order.getAskingPrice()) {
                orders.AddOrder(order);
                try {
                    new Thread(orders).start();
                    ordersPersistence.save(order);
                    ordersPersistence.update(orders);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println("Added order to buy");
            } else {
                System.out.println("Not enough money to place order to buy");
            }
        }
    }


    @Override
    public double getBalance(UserName userName) {
        return userList.getBalance(userName);
    }

    @Override
    public void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException {
        userList.transferMoney(userName, amount, isWithdraw);
        usersPersistence.update(userList.getUser(userName));
    }

    @Override
    public ArrayList<Company> getAllCompanies() {
        return companies.getCompanies();
    }

    @Override
    public Company getCompanyBySymbol(String symbol) {
        return companies.getCompanyBySymbol(symbol);
    }


    public Company getComapnyByName(String name) {
        return companies.getCompanyByName(name);
    }


    @Override
    public boolean login(User user) throws Exception {
        if (!userList.userExist(user)) {
            throw new Exception("Wrong username or password");
        }


        for (int i = 0; i < companies.getCompanies().size(); i++) {
            user.addStock(stocksPersistence.load(user, companies.getCompanies().get(i)));
            System.out.println(stocksPersistence.load(user, companies.getCompanies().get(i)));
        }

        return true;
    }


    @Override
    public boolean registerUser(User user) throws Exception {
        boolean result = userList.addUser(user);
        if (result) {
            usersPersistence.save(user);
            for (int i = 0; i < user.getStocks().getSize(); i++) {
                stocksPersistence.save(user.getStocks().getStock(i), user);
                stocksPersistence.update(user.getStocks().getStock(i));
            }
        }
        return result;
    }


}
