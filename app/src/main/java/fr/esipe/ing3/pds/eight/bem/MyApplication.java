package fr.esipe.ing3.pds.eight.bem;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * @author Warren D'ALMEIDA
 */

public class MyApplication extends Application {
	public void onCreate() {
		super.onCreate();
		Stetho.initializeWithDefaults(this);
	}
}
