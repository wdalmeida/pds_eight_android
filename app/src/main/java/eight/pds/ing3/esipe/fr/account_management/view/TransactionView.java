package eight.pds.ing3.esipe.fr.account_management.view;

import java.util.List;

import eight.pds.ing3.esipe.fr.account_management.model.Transaction;

/**
 * Created by antho on 06/02/2018.
 */

public interface TransactionView extends MvpView {
    void showTransactionList(List<Transaction> transactionList);
}
