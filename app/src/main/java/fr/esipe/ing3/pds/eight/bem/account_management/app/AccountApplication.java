package fr.esipe.ing3.pds.eight.bem.account_management.app;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import fr.esipe.ing3.pds.eight.bem.account_management.model.AccountService;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

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

    @Override
    public void onCreate() {

        super.onCreate();
        Realm.init(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

    }

}
