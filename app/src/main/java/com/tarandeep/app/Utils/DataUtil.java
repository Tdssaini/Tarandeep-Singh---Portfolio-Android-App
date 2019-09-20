package com.tarandeep.app.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Tarandeep
 */
public class DataUtil {

    public static int dipToPixels(int dipValue) {
        DisplayMetrics metrics = DataPersistence.getInstance().getApplicationContext().getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    /***
     * Read file Content and return Object
     */
    @SuppressWarnings("rawtypes")
    public static Object convertResponseIntoObject(String responseString, Class className){
        HashMap<String, Object> responseHash = getHashFromGson(responseString.toString());
        Object dataObject = null;
        if (responseHash != null)
            dataObject = getData(responseHash, className);
        return dataObject;
    }

    public static String convertToString(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 1024);
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                inputStream.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }

    public  static  int  getPxFromDip(Context context, int  dp){
        return  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void sendLogsToLogger(Context context, String logs){
        Intent sendIntent = new Intent();
        sendIntent.setAction("SEND_LOGS_TO_LOGGER");
        sendIntent.putExtra("LOG_DATA", logs);
        sendIntent.putExtra("APP_NAME", context.getPackageName());
        context.sendBroadcast(sendIntent);
    }

    public static String objectToString(Object object){
        return  new Gson().toJson(object);
    }

    public static Object stringToObject(String gsonString, Class<?> object){
        return  new Gson().fromJson(gsonString, object);
    }

    public static String getStackTrace(Throwable aThrowable) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    public static Object getData(Object arg,Class className){
        DataUtil.sendLogsToLogger(DataPersistence.getInstance().getApplicationContext(), arg.toString());
        Gson gson=new Gson();
        String gsonString=gson.toJson(arg);
        return gson.fromJson(gsonString, className);
    }

    public static HashMap<String, Object> getHashFromGson(String responseString){
        JSONObject json;
        try {
            json = new  JSONObject(responseString);
            return toMap(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static HashMap<String, Object> toMap(JSONObject object) throws JSONException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Iterator keys = object.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, fromJson(object.get(key)));
        }
        return map;
    }

    private static Object fromJson(Object json) throws JSONException {
        if (json == JSONObject.NULL) {
            return null;
        } else if (json instanceof JSONObject) {
            return toMap((JSONObject) json);
        } else if (json instanceof JSONArray) {
            return toList((JSONArray) json);
        } else {
            return json;
        }
    }

    public static ArrayList<Object> toList(JSONArray array) throws JSONException {
        ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            list.add(fromJson(array.get(i)));
        }
        return list;
    }

}
