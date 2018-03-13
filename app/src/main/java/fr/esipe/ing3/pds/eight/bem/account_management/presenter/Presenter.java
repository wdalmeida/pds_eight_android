package fr.esipe.ing3.pds.eight.bem.account_management.presenter;

/**
 * Created by antho on 12/11/2017.
 */

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
