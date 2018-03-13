package fr.esipe.ing3.pds.eight.bem.account_management.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by antho on 10/02/2018.
 */

public class TransactionDAO {

    private Long transactionId;
    private String date;
    private String description;
    private double amount;
    private String accountNumber;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public String getFormattedDate(){
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        Date javaDate = new Date(Long.parseLong(this.date));

        return sf.format(javaDate);
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
