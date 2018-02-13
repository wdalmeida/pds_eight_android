package eight.pds.ing3.esipe.fr.account_management.presenter;

import android.util.Log;

import org.reactivestreams.Subscription;

import java.io.IOException;

import eight.pds.ing3.esipe.fr.account_management.app.AccountApplication;
import eight.pds.ing3.esipe.fr.account_management.model.AccountService;
import eight.pds.ing3.esipe.fr.account_management.model.Credential;
import eight.pds.ing3.esipe.fr.account_management.view.LoginMvpView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;


/**
 * Created by antho on 29/11/2017.
 */

public class LoginPresenter implements Presenter<LoginMvpView> {

    private LoginMvpView loginMvpView;
    private Subscription subscription;
    private String token;

    @Override
    public void attachView(LoginMvpView view) {
        this.loginMvpView = view;
    }

    @Override
    public void detachView() {
        this.loginMvpView = null;

    }

    public void authenticate(String userId,String password){


        Credential credential = new Credential();
        credential.setUserId(userId);
        credential.setPassword(password);

        if(subscription !=  null) subscription.cancel();

        AccountApplication application = AccountApplication.get(loginMvpView.getContext());
        AccountService accountService = application.getAccountService();
        /*subscription = */accountService.authenticate(credential)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscriberScheduler())
                .subscribe(
                        new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody r) {


                            try {
                                LoginPresenter.this.token = r.string();
                            } catch (IOException e) {
                                loginMvpView.showLoginError();
                                e.printStackTrace();
                            }

                        Log.d("LoginPresenter",token);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loginMvpView.showLoginError();
                    }

                    @Override
                    public void onComplete() {

                        loginMvpView.startAccountActivity(token);


                    }
                });


    }
}
