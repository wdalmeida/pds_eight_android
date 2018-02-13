package eight.pds.ing3.esipe.fr.account_management.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

import eight.pds.ing3.esipe.fr.account_management.R;
import eight.pds.ing3.esipe.fr.account_management.presenter.ListAccountPresenter;
import eight.pds.ing3.esipe.fr.account_management.presenter.LoginPresenter;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginMvpView  {

    private LoginPresenter presenter;
    private CoordinatorLayout coordinatorLayout;
    private EditText userId;
    private EditText password;
    private Button b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Stetho.initializeWithDefaults(this);

        if(!isInternetConnectionAvailable())

        {
            setContentView(R.layout.activity_login);

            userId = findViewById(R.id.userid);
            password = findViewById(R.id.password);

            b = findViewById(R.id.sign_in_button);

            userId.addTextChangedListener(textWatcher);
            password.addTextChangedListener(textWatcher);

            checkIfFormIsValid();

            presenter = new LoginPresenter();
            presenter.attachView(this);

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Vous ne semblez pas avoir de connexion").setTitle("Attention");
            AlertDialog dialog = builder.create();
            dialog.show();

        }





        //findViewById(R.id.sign_in_button);
    }

    @Override
    public Context getContext() {
        return this;
    }



    @Override
    public void startAccountActivity(String token) {
        Log.d("LoginActivity","appel de ListAccountActivity");
        Intent intent = new Intent(getContext(),ListAccountActivity.class);
        intent.putExtra("token",token);
        startActivity(intent);
    }

    @Override
    public void showLoginError() {
        Log.d("LoginActivity", "showLoginError: ");
        Snackbar snackbar = Snackbar.make(findViewById(R.id.loginActivityLinearLayout),"Informations de connexion incorrecte",Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(Color.WHITE);
        snackbar.show();
    }

    public void onSignInButtonClick(View view) {


        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        presenter.authenticate(userId.getText().toString(),password.getText().toString());
    }

    private void checkIfFormIsValid(){

        Log.d("LoginActivity", "checkIfFormIsValid: userId ["+userId.getText().toString());

        if(userId.getText().toString().equals("") || password.getText().toString().equals("")){
            b.setEnabled(false);
        }
        else {
            b.setEnabled(true);
        }

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            checkIfFormIsValid();
        }

        @Override
        public void afterTextChanged(Editable editable) {


        }
    };

    public boolean isInternetConnectionAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnectedOrConnecting() ){
            return false;
        }
        else return true;
    }
}

