package fr.esipe.ing3.pds.eight.bem.account_management.view;

/**
 * Created by antho on 29/11/2017.
 */

public interface LoginMvpView extends MvpView {



    void startAccountActivity(String token);

    void showLoginError();
}
