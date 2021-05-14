package model;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Email class representing email of the user
 */

public class Email implements Serializable {
    private String name;
    private String domain;

    /**
     * Constructor which is setting the email
     * @param email email of the user
     */

    public Email(String email){
        setEmail(email);
    }
    /**
     * Constructor which is setting both instance variables
     * @param name name of the user
     * @param domain domain of the user
     */

    public Email(String name, String domain){
        this(name+"@"+domain);
    }

    /**
     * setting up the email
     * @param email email that user chooses
     */

    public void setEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!Pattern.compile(regex).matcher(email).matches()){
            throw new IllegalArgumentException("Not a valid email");
        }
        name = email.split("@")[0];
        domain = email.split("@")[1];
    }

    /**
     * getting the toString version of the email
     * @return email
     */

    public String getEmail(){
        return toString();
    }

    /**
     * getting the name
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * getting the domain
     * @return domain
     */

    public String getDomain() {
        return domain;
    }

    /**
     * toString version of the email
     * @return email
     */

    @Override
    public String toString(){
        return name+"@"+domain;
    }
}
