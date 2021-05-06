package model;

import java.math.BigDecimal;

/**
 * Balance class represents balance of the user
 */

public class Balance
{
    private BigDecimal balance;

    /**
     * Constructor which is initialing instance variable
     */

    public Balance(){
        this.balance = new BigDecimal("0.0");
    }

    /**
     * adding amount of money on balance
     * @param amount amount to be added
     */

    public void add(double amount){
        balance = balance.add(new BigDecimal(amount));
    }

    /**
     * withdrawing money from balance
     * @param amount amount that is going to be withdrawn
     */

    public void withDraw(double amount){
        if (amount<0){
            throw new IllegalArgumentException("Enter a positive value");
        }
        if ((balance.doubleValue()-amount)<0){
            throw new IllegalArgumentException("Can not withdraw below zero");
        }
        balance = balance.subtract(new BigDecimal(amount));
    }

    /**
     * getting the balace
     * @return balance
     */

    public double getBalance(){
        return balance.doubleValue();
    }
}
