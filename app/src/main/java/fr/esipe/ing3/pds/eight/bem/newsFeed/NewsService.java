package fr.esipe.ing3.pds.eight.bem.newsFeed;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * @author Warren D'ALMEIDA
 */

public class NewsService extends FirebaseInstanceIdService {
	private static final String TAG = "FirebaseIDService";

	@Override
	public void onTokenRefresh() {
		// Get updated InstanceID token.
		String refreshedToken = FirebaseInstanceId.getInstance().getToken();
		Log.d(TAG, "Refreshed token: " + refreshedToken);
	}
}