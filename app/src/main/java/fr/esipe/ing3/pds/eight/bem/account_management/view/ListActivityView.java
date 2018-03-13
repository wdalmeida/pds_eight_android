package fr.esipe.ing3.pds.eight.bem.account_management.view;

import java.util.List;

import fr.esipe.ing3.pds.eight.bem.account_management.model.Account;

/**
 * Created by antho on 12/11/2017.
 */

public interface ListActivityView extends MvpView {

    void showAccountList(List<Account> accountList);


}
