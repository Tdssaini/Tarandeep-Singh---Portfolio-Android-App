package com.tarandeep.app.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	private View bmImage;
    private String urldisplay=null;

    public DownloadImageTask(View bmImage) {
		this.bmImage = bmImage;
	}

	@Override
	protected void onPreExecute() {

		super.onPreExecute();
	}

	protected Bitmap doInBackground(String... urls) {
        urldisplay = urls[0];
		Bitmap mIcon11 = null;
		try {
			InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return mIcon11;
	}

	@Override 
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
        if(result==null || bmImage==null)
            return;
		if(bmImage instanceof  ImageView) {
			((ImageView) bmImage).setImageBitmap(result);
		}
	}
}