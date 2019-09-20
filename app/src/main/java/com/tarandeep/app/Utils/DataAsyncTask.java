package com.tarandeep.app.Utils;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class DataAsyncTask extends AsyncTask<String, Void, String>{

	TDSProgressBar progressBar;
	Context context;

	public DataAsyncTask(Context context) {
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressBar = new TDSProgressBar(context){
			@Override
			public void onDismiss() {}
		};
		progressBar.show();
	}

	@Override
	protected String doInBackground(String... params) {
		String responseStr = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpUriRequest httpUriRequest = new HttpGet(params[0]);
			HttpResponse response = httpClient.execute(httpUriRequest);
			responseStr = DataUtil.convertToString(response.getEntity().getContent());
		} catch (IOException e) {
			System.out.println(e);
		}
		return responseStr;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if(progressBar!=null && progressBar.isShowing())
			progressBar.hide();
	}
	
}
