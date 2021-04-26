package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private String name;
    private String domain;

    public Email(String email){
        setEmail(email);
    }

    public Email(String name, String domain){
        this(name+"@"+domain);
    }

    public void setEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!Pattern.compile(regex).matcher(email).matches()){
            throw new IllegalArgumentException("Not a valid email");
        }
        name = email.split("@")[0];
        domain = email.split("@")[1];
    }

    public String getEmail(){
        return toString();
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString(){
        return name+"@"+domain;
    }
}
