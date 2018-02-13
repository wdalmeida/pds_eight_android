package eight.pds.ing3.esipe.fr.account_management.view;

import java.util.List;

import eight.pds.ing3.esipe.fr.account_management.model.Account;

/**
 * Created by antho on 12/11/2017.
 */

public interface ListAccountView extends MvpView {

    void showAccountList(List<Account> accountList);


}
