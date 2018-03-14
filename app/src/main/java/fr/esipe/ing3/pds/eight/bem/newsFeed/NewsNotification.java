package fr.esipe.ing3.pds.eight.bem.newsFeed;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * @author Warren D'ALMEIDA
 */

public class NewsNotification extends FirebaseMessagingService {
	private static final String TAG = "FCM";
	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		Log.d(TAG, "From: " + remoteMessage.getFrom());
		Log.d(TAG, "Notification Message: " + remoteMessage.getNotification().getBody());
	}
}
