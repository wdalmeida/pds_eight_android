package fr.esipe.ing3.pds.eight.bem;

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
		sendRegistrationToServer(refreshedToken);
	}

	/**
	 * Persist token to third-party servers.
	 *
	 * Modify this method to associate the user's FCM InstanceID token with any server-side account
	 * maintained by your application.
	 *
	 * @param token The new token.
	 */
	private void sendRegistrationToServer(String token) {
		// Add custom implementation, as needed.
	}
}