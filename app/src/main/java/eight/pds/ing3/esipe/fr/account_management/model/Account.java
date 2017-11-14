package eight.pds.ing3.esipe.fr.account_management.model;

/**
 * Created by antho on 12/11/2017.
 */

public class Account {

private String accountId;
    private String type;
    private float balance;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
