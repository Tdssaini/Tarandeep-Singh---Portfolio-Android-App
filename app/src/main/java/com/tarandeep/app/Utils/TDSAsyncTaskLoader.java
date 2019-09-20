package com.tarandeep.app.Utils;


import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

public class TDSAsyncTaskLoader extends AsyncTask<String, String, Object>{

	protected Context context;
	public TDSProgressBar progressAlertDialog=null;
	protected AsyncTaskLoaderListener taskListener;
	private String url;
	private ArrayList<NameValuePair> requestHeaderList;
	private ArrayList<NameValuePair> requestBodyList;
	private String requestBody;
	private String methodType;
	private boolean showProgressBar = true;

	public TDSAsyncTaskLoader(Context context, AsyncTaskLoaderListener taskListener) {
		this.context=context;
		this.taskListener = taskListener;
		this.showProgressBar = true;
	}

	public TDSAsyncTaskLoader(Context context, AsyncTaskLoaderListener taskListener,boolean showProgressBar) {
		this.context=context;
		this.taskListener = taskListener;
		this.showProgressBar = showProgressBar;
	}

	/**
	 * @param methodType
	 * Values for method type should be IDS.POST or IDS.GET
	 */
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	/**
	 * @param url
	 * url is the complete url that is the combination of domain url + actionIID
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param requestHeaderList
	 * request Header List is the list of header key value pairs that is to be send in header. ArrayList should be of
	 * NameValuePair.
	 */
	public void setRequestHeaderList(ArrayList requestHeaderList) {
		this.requestHeaderList = requestHeaderList;
	}

	/**
	 * @param requestBodyList
	 * request Body List is the list of body key value pairs that is to be send in body. ArrayList should be of
	 * NameValuePair.
	 */
	public void setRequestBodyList(ArrayList requestBodyList) {
		this.requestBodyList = requestBodyList;
	}

	/**
	 * @param requestBody
	 * request body is the string text (it can be json) that is to be send in body.
	 */
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}


	@Override
	protected void onPreExecute() {
		if(showProgressBar)
        	showProgressDialog(context);
	}

	@Override
	protected Object doInBackground(String... params) {
		DataUtil.sendLogsToLogger(context, url);
		if(requestHeaderList!=null) {
			for (NameValuePair header : requestHeaderList)
				DataUtil.sendLogsToLogger(context, header.getName() + " " + header.getValue());
		}
		if(requestBodyList!=null) {
			for (NameValuePair body : requestBodyList) {
				DataUtil.sendLogsToLogger(context, body.getName() + " " + body.getValue());
			}
		}
		if(requestBody!=null)
			DataUtil.sendLogsToLogger(context,requestBody);

		try{
			if(methodType.equals(IDS.POST)){
				return new ServerCommunication().postDataBasic(url,requestBody,requestBodyList,requestHeaderList);
			}else if(methodType.equals(IDS.GET)){
				return new ServerCommunication().getData(url,requestBodyList,requestHeaderList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Object result) {
		try {
			if (progressAlertDialog!=null) {
				progressAlertDialog.dismiss();
				progressAlertDialog.cancel();
			}

			taskListener.onPostExecute(result);

		}catch (Exception e){
			e.printStackTrace();
			DataUtil.sendLogsToLogger(context, DataUtil.getStackTrace(e));
		}
    }


	public void showProgressDialog(Context activity){
		progressAlertDialog=new TDSProgressBar(activity) {
			@Override
			public void onDismiss() {}
		};
		progressAlertDialog.show();
	}

	public interface AsyncTaskLoaderListener{
		void onPostExecute(Object result) throws Exception;
	}

}