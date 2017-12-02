package eight.pds.ing3.esipe.fr.account_management.view;

import android.content.Context;

/**
 * Created by antho on 29/11/2017.
 */

public interface LoginMvpView extends MvpView {



    void startAccountActivity(String token);

    void showLoginError();
}
