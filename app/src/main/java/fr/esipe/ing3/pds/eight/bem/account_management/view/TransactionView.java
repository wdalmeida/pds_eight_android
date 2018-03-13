package fr.esipe.ing3.pds.eight.bem.account_management.view;

import java.util.List;

import fr.esipe.ing3.pds.eight.bem.account_management.model.Transaction;

/**
 * Created by antho on 06/02/2018.
 */

public interface TransactionView extends MvpView {
    void showTransactionList(List<Transaction> transactionList);
}
