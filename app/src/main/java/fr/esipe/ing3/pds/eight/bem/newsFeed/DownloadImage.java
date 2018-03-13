package fr.esipe.ing3.pds.eight.bem.newsFeed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * @author Warren D'ALMEIDA
 */

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

	private ImageView imgv;
	private Bitmap bit;

	public DownloadImage(ImageView img) {
		this.imgv = img;
	}

	@Override
	protected Bitmap doInBackground(String... geturl) {
		try {
			Log.d(TAG, "doInBackground: "+ geturl[0]);
			URL url = new URL(geturl[0]);
			bit = BitmapFactory.decodeStream(url.openStream());
		} catch (IOException e) {
			Log.d(TAG, "doInBackground: Error");
			e.printStackTrace();
		}
		return bit;
	}

	@Override
	protected void onPostExecute(Bitmap bit) {
		Log.d(TAG, "onPostExecute: set image");
		imgv.setImageBitmap(bit);
	}

}
