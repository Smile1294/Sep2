package persistence;

import model.User;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserListFile implements UserFilePersistence {
    private File file;
    private Connection con;
    PreparedStatement pst;

    public UserListFile(String filename) {

        file = new File(filename);
        con = null;
        pst = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            file.createNewFile();
        } catch (IOException | SQLException e) {
            System.out.println("An error occurred with Profiles file.");
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<User> load() throws IOException {

        ArrayList<User> userArrayList = new ArrayList<>();
        Reader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        if (line != null) {
            List<String> userListString = new ArrayList<>(Arrays.asList(line.split(";:;")));
            for (int x = 0; x < userListString.size(); x = x + 2) {
                userArrayList.add(new User(userListString.get(x), userListString.get(x + 1)));
            }
        }
        bufferedReader.close();
        reader.close();
        return userArrayList;
    }

    public void addUser(User user) throws IOException {
        String sql = "Insert into testingofjavasaving.userofapplication(name,password,ballance)values (?,?,?);";

        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","");
            pst = con.prepareStatement(sql);
            pst.setString(1,user.getName());
            pst.setString(2, user.getPassword());
            pst.setString(3,String.valueOf(user.getBalance()));
            pst.execute();
            con.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        FileWriter writer = new FileWriter(file, true);
        writer.write(user.getName() + ";:;" + user.getPassword() + ";:;");

        writer.close();


    }
}
