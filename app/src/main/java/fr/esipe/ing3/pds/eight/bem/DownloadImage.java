package fr.esipe.ing3.pds.eight.bem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * @author Warren D'ALMEIDA
 */

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

	private ImageView imgv;

	public DownloadImage(ImageView img) {
		this.imgv = img;
	}

	@Override
	protected Bitmap doInBackground(String... geturl) {
		BitmapFactory bf =null;
		Bitmap bit = null;
		Log.d(TAG, "doInBackground: "+ geturl[0].toString());
		try {
			URL url = new URL(geturl[0]);
			bit = BitmapFactory.decodeStream(url.openStream());
		} catch (MalformedURLException e) {
			Log.d(TAG, "doInBackground: HERE");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d(TAG, "doInBackground: HEREEEEEEEE");

			e.printStackTrace();
		}
		return  bit;
	}

	protected void onPostExecute(RSSFeed feed) {
	}
}
