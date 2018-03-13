package fr.esipe.ing3.pds.eight.bem.account_management.model;

import java.io.Serializable;

/**
 * Created by antho on 12/11/2017.
 */

public class Account implements Serializable {

private String accountNumber;
    private String type;
    private float balance;

    public String getAccountId() {
        return accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
