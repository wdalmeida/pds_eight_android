package fr.esipe.ing3.pds.eight.bem.account_management.view;

import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import fr.esipe.ing3.pds.eight.bem.R;
import fr.esipe.ing3.pds.eight.bem.cardpayement.NFCManager;
import fr.esipe.ing3.pds.eight.bem.newsFeed.NewsActivity;


public class HomeActivity extends AppCompatActivity implements MvpView {

	TextView customerName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Intent intent = getIntent();
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
			Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			NFCManager.getInstance().writeToDevice("5151343478789090", tag);
		}
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
		initManager();

		if (NFCManager.getInstance().checkDeviceHasNFC()) {
			setContentView(R.layout.activity_home);
		} else
			setContentView(R.layout.activity_home);

	}

	private void initManager() {
		NFCManager.getInstance().setContext(this);
	}




	public void onClick(View view) {
		Intent intent = new Intent(getContext(),LoginActivity.class);
		startActivity(intent);
	}

	public void onClickNews(View view) {
		Intent intent = new Intent(getContext(),NewsActivity.class);
		startActivity(intent);
	}
	@Override
	public Context getContext() {
		return this;
	}
}
