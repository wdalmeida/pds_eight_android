package eight.pds.ing3.esipe.fr.account_management.presenter;





import android.util.Log;

import org.reactivestreams.Subscription;

import java.util.List;

import eight.pds.ing3.esipe.fr.account_management.app.AccountApplication;
import eight.pds.ing3.esipe.fr.account_management.model.Account;
import eight.pds.ing3.esipe.fr.account_management.model.AccountService;
import eight.pds.ing3.esipe.fr.account_management.view.ListAccountView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by antho on 12/11/2017.
 */

public class ListAccountPresenter implements Presenter<ListAccountView> {

    private ListAccountView listActivityView;
    private Subscription subscription;
    private List<Account> accountList;

    @Override
    public void attachView(ListAccountView view) {
        this.listActivityView = view;
    }

    @Override
    public void detachView() {
        this.listActivityView = null;


    }

    public void loadAccounts(String jwtToken){

        if(subscription !=  null) subscription.cancel();

        AccountApplication application = AccountApplication.get(listActivityView.getContext());

        String authHeader = "Bearer " + jwtToken;




        AccountService accountService = application.getAccountService();
        /*subscription = */accountService.getAccounts(authHeader)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscriberScheduler())
                .subscribe(new Observer<List<Account>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("ListAccountPresenter","OnSubscribe");

                    }

                    @Override
                    public void onNext(@NonNull List<Account> accountList) {
                        ListAccountPresenter.this.accountList = accountList;
                        Log.d("ListAccountPresenter", "onNext: accountId = "+ListAccountPresenter.this.accountList.get(0).getType());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ListAccountPresenter","OnError");

                    }

                    @Override
                    public void onComplete() {
                        listActivityView.showAccountList(accountList);
                        Log.d("ListAccountPresenter","OnComplete");
                    }
                });
    }
}
