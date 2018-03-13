package fr.esipe.ing3.pds.eight.bem.newsFeed;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author Warren D'ALMEIDA
 */

public class RssWebView extends AppCompatActivity {

	private static final String TAG ="RssWebView" ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String url = getIntent().getStringExtra("EXTRA_SESSION_ID");
		Log.d(TAG, "onCreate: " +url);


		// create an intent builder
		CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();

		// build custom tabs intent
		CustomTabsIntent customTabsIntent = intentBuilder.build();
		final Activity activity = this;

		// launch the url
		customTabsIntent.launchUrl(activity,Uri.parse(url));
		finish();
	}
}
