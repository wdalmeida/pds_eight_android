package fr.esipe.ing3.pds.eight.bem.account_management.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import fr.esipe.ing3.pds.eight.bem.R;


public class HomeActivity extends AppCompatActivity implements MvpView {

    TextView customerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    /*    SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String firstName = sharedPref.getString("firstName","none");
        String lastName = sharedPref.getString("lastName","none");

        Log.d("HomeActivity", "onCreate: firstName" + firstName);
        Log.d("HomeActivity", "onCreate: lastName" + lastName); */

     //   if(!firstName.equals("none") && !lastName.equals("none")){
        // customerName =   findViewById(R.id.hello_textView);
         //customerName.setText(firstName + " " + lastName);
        // customerName.setVisibility(View.VISIBLE);
       // }


    }

    public void onClick(View view) {
        Intent intent = new Intent(getContext(),LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
