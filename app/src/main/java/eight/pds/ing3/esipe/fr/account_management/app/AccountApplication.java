package eight.pds.ing3.esipe.fr.account_management;

import android.app.Application;
import android.content.Context;

import eight.pds.ing3.esipe.fr.account_management.model.AccountService;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by antho on 12/11/2017.
 */

public class AccountApplication extends Application {


    private AccountService accountService;
    private Scheduler subscriberScheduler;

    public static AccountApplication get(Context context){
        return (AccountApplication) context.getApplicationContext();
    }

    public AccountService getAccountService(){
        if(accountService == null){
            accountService = AccountService.Creator.newAccountService();
        }
        return accountService;
    }

    public Scheduler defaultSubscriberScheduler(){
        if(subscriberScheduler == null){
            subscriberScheduler = Schedulers.io();
        }
        return subscriberScheduler;
    }

}
