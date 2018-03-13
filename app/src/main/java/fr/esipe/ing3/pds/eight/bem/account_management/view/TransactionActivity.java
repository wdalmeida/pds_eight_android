package fr.esipe.ing3.pds.eight.bem.account_management.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.esipe.ing3.pds.eight.bem.account_management.TransactionAdapter;
import fr.esipe.ing3.pds.eight.bem.account_management.model.Account;
import fr.esipe.ing3.pds.eight.bem.account_management.model.Transaction;
import fr.esipe.ing3.pds.eight.bem.account_management.presenter.TransactionPresenter;
import fr.esipe.ing3.pds.eight.bem.R;

public class TransactionActivity extends AppCompatActivity implements TransactionView {

    private TransactionPresenter presenter;
    private RecyclerView transactionRecyclerView;

    SearchView searchView;
    private TextView accountNumberTextView;
    private TextView accountTypeTextView;
    private TextView balanceTextView;
    private TextView todayTextView;
    private View contentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction2);



            String jwtToken = getIntent().getStringExtra("token");
            Account account = (Account) getIntent().getSerializableExtra("Account");

            accountNumberTextView = findViewById(R.id.text_account_number);
            accountTypeTextView = findViewById(R.id.text_account_type);
            balanceTextView = findViewById(R.id.text_balance);
            todayTextView = findViewById(R.id.text_today);

            searchView = findViewById(R.id.searchView);

            searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);

            }
            });


            contentLayout = findViewById(R.id.name_header2);

            accountNumberTextView.setText("N° " + account.getAccountId());
            accountTypeTextView.setText(account.getType());

        if(account.getBalance() > 0)
            balanceTextView.setText("+ "+String.valueOf(account.getBalance())+" €");
        else balanceTextView.setText(String.valueOf(account.getBalance())+" €");

            SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
            Date today = new Date();

            todayTextView.setText("SOLDE AU "+sf.format(today));

            if (account.getType().equals("CCB")) {
                contentLayout.setBackgroundColor(Color.parseColor("#D12566"));

            } else {
                contentLayout.setBackgroundColor(Color.parseColor("#136CBD"));

            }


            presenter = new TransactionPresenter();
            presenter.attachView(this);


            transactionRecyclerView = findViewById(R.id.transaction_recycler_view);
            setupRecyclerView(transactionRecyclerView);

            if(isItFirstTime(account.getAccountId()))
                presenter.loadTransactions(jwtToken, account);
            else
                presenter.loadLocalTransactions(account.getAccountNumber());
        }


    private void setupRecyclerView(RecyclerView recyclerView) {
        TransactionAdapter adapter = new TransactionAdapter();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showTransactionList(List<Transaction> transactionList) {
        //Log.d("TransactionActivity","contenu liste transactions" + transactionList.get(0).getDescription());
        TransactionAdapter adapter = (TransactionAdapter) transactionRecyclerView.getAdapter();
        adapter.setTransactionList(transactionList);
        adapter.notifyDataSetChanged();
        transactionRecyclerView.requestFocus();
        transactionRecyclerView.setVisibility(View.VISIBLE);


    }


    private boolean isItFirstTime(String accountNumber){
        SharedPreferences sharedPreferences = getSharedPreferences("STATE",MODE_PRIVATE);
        boolean  b = sharedPreferences.getBoolean("state"+accountNumber,true);

        return b;
    }



}
