package com.tarandeep.app.Utils;

import android.os.AsyncTask;

import com.tarandeep.app.Fragments.FragResume;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadPdfTask extends AsyncTask<String, Void, String> {

    private TDSProgressBar progressBar;
	private boolean isDownloadOnDevice;

	public DownloadPdfTask(TDSProgressBar progressBar){
		this.progressBar = progressBar;
		this.isDownloadOnDevice = false;
	}

	public DownloadPdfTask(TDSProgressBar progressBar,boolean isDownloadOnDevice){
		this.progressBar = progressBar;
		this.isDownloadOnDevice = isDownloadOnDevice;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
        if(this.progressBar !=null)
            progressBar.show();
	}

	protected String doInBackground(String... urls) {
		String filepath = "ERROR";
		try {
			URL url = new URL(urls[0]);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.connect();
			File temp = null;
			if(isDownloadOnDevice){
				temp = new File(FragResume.getParentDirectory(),"Resume.pdf");
			}else {
			    temp =File.createTempFile("CurrentPDF", ".pdf");
			}
			if(temp.createNewFile()) {
                temp.createNewFile();
			}
			FileOutputStream fileOutput = new FileOutputStream(temp);
			InputStream inputStream = urlConnection.getInputStream();
			int totalSize = urlConnection.getContentLength();
			int downloadedSize = 0;
			byte[] buffer = new byte[1024];
			int bufferLength = 0;
			while ( (bufferLength = inputStream.read(buffer)) > 0 )
			{
				fileOutput.write(buffer, 0, bufferLength);
				downloadedSize += bufferLength;
			}
			fileOutput.close();
			if(downloadedSize==totalSize)
			    filepath=temp.getPath();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return filepath;
	}

	@Override 
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
	}
}