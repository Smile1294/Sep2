package persistence;

import model.*;

import java.sql.*;

public class UsersDatabase implements UsersPersistence{
    private static UsersDatabase instance;

    private UsersDatabase() { }

    public static synchronized UsersDatabase getInstance(){
        if (instance == null){
            instance = new UsersDatabase();
        }
        return instance;
    }

    @Override
    public UserList load() throws SQLException {
        try (Connection connection = GetConnection.get()){
            PreparedStatement statement = connection.prepareStatement("select * from Customer");
            ResultSet resultSet = statement.executeQuery();
            UserList users = new UserList();

            while (resultSet.next()){
                String userName = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                int balance = resultSet.getInt("money_balance");
                User user = new User(new UserName(userName), new Password(password),new Email(email),balance);
                try {
                    users.addUser(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return users;
        }
    }

    @Override
    public void update(User user) throws SQLException{
        try (Connection connection = GetConnection.get()){
            PreparedStatement statement = connection.prepareStatement("update customer set money_balance = ? where user_name = ?");
            statement.setInt(1,user.getBalance().intValue());
            statement.setString(2,user.getUserName().toString());
            statement.executeUpdate();
        }
    }

    @Override
    public void save(User user) throws SQLException{
        try (Connection connection = GetConnection.get()){
            PreparedStatement statement = connection.prepareStatement("insert into customer(user_name,password,email,money_balance)values (?,?,?,?)");
            statement.setString(1,user.getUserName().toString());
            statement.setString(2,user.getPassword().toString());
            statement.setString(3,user.getEmail().toString());
            statement.setInt(4,user.getBalance().intValue());
            statement.executeUpdate();
        }
    }

    @Override
    public void remove(User user) throws SQLException{
        try (Connection connection = GetConnection.get()){
            PreparedStatement statement = connection.prepareStatement("delete from Customer where user_name = ?");
            statement.setString(1,user.getUserName().toString());
            statement.executeUpdate();
        }
    }
}
