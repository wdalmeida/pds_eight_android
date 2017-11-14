package eight.pds.ing3.esipe.fr.account_management.presenter;

/**
 * Created by antho on 12/11/2017.
 */

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
