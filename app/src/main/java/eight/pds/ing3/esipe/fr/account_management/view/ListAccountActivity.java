package eight.pds.ing3.esipe.fr.account_management.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import eight.pds.ing3.esipe.fr.account_management.AccountAdapter;
import eight.pds.ing3.esipe.fr.account_management.R;
import eight.pds.ing3.esipe.fr.account_management.model.Account;
import eight.pds.ing3.esipe.fr.account_management.presenter.ListAccountPresenter;

public class ListAccountActivity extends AppCompatActivity implements ListActivityView {

    private static final String TAG = "ListAccountActivity" ;
    private ListAccountPresenter presenter;
    private RecyclerView accountRecyclerView;
    private TextView infoTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ListAccountPresenter();
        presenter.attachView(this);
        setContentView(R.layout.activity_list_account);

        accountRecyclerView = (RecyclerView) findViewById(R.id.account_recycler_view);
        setupRecyclerView(accountRecyclerView);
        presenter.loadAccounts();

    }

    @Override
    public void showAccountList(List<Account> accountList) {
        Log.d(TAG,"contenu liste " + accountList.get(0).getAccountId());
        AccountAdapter adapter = (AccountAdapter) accountRecyclerView.getAdapter();
        adapter.setAccountList(accountList);
        adapter.notifyDataSetChanged();
        accountRecyclerView.requestFocus();
        accountRecyclerView.setVisibility(View.VISIBLE);
    }



    @Override
    public Context getContext() {
        return this;
    }

    private void setupRecyclerView(RecyclerView recyclerView){
        AccountAdapter adapter = new AccountAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



}
