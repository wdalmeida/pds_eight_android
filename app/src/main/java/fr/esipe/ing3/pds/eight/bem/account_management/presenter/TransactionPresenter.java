package fr.esipe.ing3.pds.eight.bem.account_management.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.esipe.ing3.pds.eight.bem.account_management.model.Account;
import fr.esipe.ing3.pds.eight.bem.account_management.app.AccountApplication;
import fr.esipe.ing3.pds.eight.bem.account_management.model.AccountService;
import fr.esipe.ing3.pds.eight.bem.account_management.model.Transaction;
import fr.esipe.ing3.pds.eight.bem.account_management.view.TransactionView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;

/**
 * Created by antho on 06/02/2018.
 */

public class TransactionPresenter implements Presenter<TransactionView> {


    private TransactionView transactionView;
    private Subscription subscription;
    private List<Transaction> transactionList;

    private Realm realm = Realm.getDefaultInstance();

    @Override
    public void attachView(TransactionView view) {
        this.transactionView = view;
    }

    @Override
    public void detachView() {
        this.transactionView = null;
    }



    public void loadTransactions(String jwtToken, final Account account){

        if(subscription !=  null) subscription.cancel();

        AccountApplication application = AccountApplication.get(transactionView.getContext());

        String authHeader = "Bearer " + jwtToken;




        AccountService accountService = application.getAccountService();
        /*subscription = */accountService.getTransactions(account.getAccountId(),authHeader)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscriberScheduler())
                .subscribe(new Observer<List<Transaction>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("TransactionPresenter","OnSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull List<Transaction> transactionList) {
                        TransactionPresenter.this.transactionList = transactionList;
                        Log.d("TransactionPresenter", "onNext: Description = "+TransactionPresenter.this.transactionList.get(0).getDescription());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("TransactionPresenter","OnError" + e.getMessage());
                        loadLocalTransactions(account.getAccountNumber());

                    }

                    @Override
                    public void onComplete() {
                        transactionView.showTransactionList(transactionList);
                        //List<TransactionDAO> transactionDAOList = convertList(transactionList,account.getAccountNumber());
                        transactionList.forEach(t->t.setAccountNumber(account.getAccountNumber()));
                        Log.d("TransactionPresenter",transactionList.get(0).getAccountNumber());
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(transactionList);
                        realm.commitTransaction();
                        savePreferences(account.getAccountNumber());
                        Log.d("TransactionPresenter","OnComplete");
                    }
                });
    }

    public void loadLocalTransactions(String accountNumber){

        ArrayList<Transaction> list = new ArrayList(realm.where(Transaction.class).equalTo("accountNumber",accountNumber).findAll());
        if(list != null)
        transactionList = list;
        else transactionList = Collections.EMPTY_LIST;
        transactionView.showTransactionList(transactionList);


    }

    private void savePreferences(String accountNumber){
        SharedPreferences sharedPreferences = transactionView.getContext().getSharedPreferences("STATE", Context.MODE_PRIVATE) ;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("state"+accountNumber, false);
        editor.apply();
    }




}
