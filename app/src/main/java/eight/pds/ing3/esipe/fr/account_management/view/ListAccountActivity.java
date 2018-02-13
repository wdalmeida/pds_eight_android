package eight.pds.ing3.esipe.fr.account_management.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import eight.pds.ing3.esipe.fr.account_management.AccountAdapter;
import eight.pds.ing3.esipe.fr.account_management.R;
import eight.pds.ing3.esipe.fr.account_management.model.Account;
import eight.pds.ing3.esipe.fr.account_management.presenter.ListAccountPresenter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ListAccountActivity extends AppCompatActivity implements ListAccountView {

    private static final String TAG = "ListAccountActivity" ;
    private ListAccountPresenter presenter;
    private RecyclerView accountRecyclerView;
    private TextView nameTextView;
    private String jwtToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_account);


       /*String*/ jwtToken = getIntent().getStringExtra("token");
      Claims claims =  Jwts.parser().setSigningKey("MTIzc2Z2MWU2djFlcnYxOThlcjF2NXYxOWU4YjFlNjViMTY1ZWYxYnY5OGU0ZmI".getBytes()).parseClaimsJws(jwtToken).getBody();
        String firstName = claims.get("firstName",String.class);
      String lastName = claims.get("lastName",String.class);

      SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPref.edit();
      editor.putString("firstName",firstName);
      editor.putString("lastName",lastName);
      editor.commit();




       nameTextView = findViewById(R.id.name_textView);
       nameTextView.setText(lastName +" " + firstName);

        presenter = new ListAccountPresenter();
        presenter.attachView(this);


        accountRecyclerView = (RecyclerView) findViewById(R.id.account_recycler_view);
        setupRecyclerView(accountRecyclerView);
        presenter.loadAccounts(jwtToken);

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
        AccountAdapter adapter = new AccountAdapter(new AccountAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Account account) {
              //  Toast.makeText(getContext(), "Item Clicked" + account.getAccountId(), Toast.LENGTH_LONG).show();


                    Log.d("ListAccountActivity","appel de TransactionActivity");
                    Intent intent = new Intent(getContext(),TransactionActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("token",jwtToken);
                    intent.putExtra("Account",account);
                    startActivity(intent);


            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Souhaitez-vous être déconnecté de l'application ?").setTitle("Attention")
        .setPositiveButton("Me déconnecter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent =new Intent(getContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        })
        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
        ;
        AlertDialog dialog = builder.create();
        dialog.show();

    }


}
