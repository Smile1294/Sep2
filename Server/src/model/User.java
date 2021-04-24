package model;

public class User {
    private Balance balance;
    private UserName userName;
    private Password password;

    public User(UserName userName, Password password) {
        if (userName == null || password == null)
        {
            throw new IllegalArgumentException("Null username or password");
        }
        this.userName = userName;
        this.password = password;
        this.balance = new Balance();
    }

    public User(UserName userName, Password password, Password passwordConfirm) {
        if (userName == null || password == null || passwordConfirm == null)
        {
            throw new IllegalArgumentException("Null username or password");
        }
        if (!password.equals(passwordConfirm)){
            throw new IllegalArgumentException("Passwords does not match");
        }
        this.userName = userName;
        this.password = password;
        this.balance = new Balance();
    }

    public Double getBalance() {
        return balance.getBalance();
    }

    public UserName getUserName() {
        return userName;
    }

    public Password getPassword() {
        return password;
    }

    public void addCash(double amount){
        balance.add(amount);
    }

    public void withDraw(double amount){
        balance.withDraw(amount);
    }

    @Override public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (o == this){
            return true;
        }
        if (o instanceof User){
            User other = (User) o;
            return userName.equals(other.userName) && password.equals(other.password);
        }
        return false;
    }

}
