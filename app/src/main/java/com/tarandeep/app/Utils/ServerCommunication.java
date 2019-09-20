package com.tarandeep.app.Utils;

import android.support.annotation.NonNull;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

public class ServerCommunication {

    private DefaultHttpClient client = new DefaultHttpClient();

    /**
     *
     * @param url
     * @param requestBody
     * @param requestBodyList
     * @param requestHeaderList
     * @return
     *
     * In this method url can't be null, out of requestBody and requestBodyList one should not be null and other have to null as
     * this method checks requestBody and if requestBody is not null, it uses it else it goes with requestBodyList. Thirdly
     * requestHeaderList can be null as per your requirement. In this we have default application/json as accept param
     *
     * url : @NotNull
     * requestBody | requestBodyList : @NotNull (At least one can't be null)
     * requestHeaderList : (Can be or can't be null)
     */
    public String postData(@NonNull String url, String requestBody, ArrayList<NameValuePair> requestBodyList,
                           ArrayList<NameValuePair> requestHeaderList){

        HttpPost httpPost= new HttpPost();

        try {
            if (requestBody!=null && !requestBody.trim().equals("")) {
                httpPost.setURI(new URI(url));
                httpPost.setEntity(new ByteArrayEntity(requestBody.toString().getBytes("UTF8")));
                httpPost.addHeader("Accept", "application/json");
                if(requestHeaderList!=null) {
                    for (int i = 0; i < requestHeaderList.size(); i++) {
                        httpPost.addHeader(requestHeaderList.get(i).getName(), requestHeaderList.get(i).getValue());
                    }
                }
                HttpResponse response = client.execute(httpPost);
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }else{
                httpPost.setURI(new URI(url));
                httpPost.addHeader("Accept", "application/json");
                if(requestHeaderList!=null) {
                    for (int i = 0; i < requestHeaderList.size(); i++) {
                        httpPost.addHeader(requestHeaderList.get(i).getName(), requestHeaderList.get(i).getValue());
                    }
                }
                httpPost.setEntity(new UrlEncodedFormEntity(requestBodyList));
                HttpResponse response = client.execute(httpPost);
                HttpEntity httpentity = response.getEntity();
                InputStream is = httpentity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is),8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine())!= null) {
                    sb.append(line+"\n");
                }
                is.close();
                return sb.toString();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param url
     * @param requestBody
     * @param requestBodyList
     * @param requestHeaderList
     * @return
     *
     * In this method url can't be null, out of requestBody and requestBodyList one should not be null and other have to null as
     * this method checks requestBody and if requestBody is not null, it uses it else it goes with requestBodyList. Thirdly
     * requestHeaderList can be null as per your requirement. In this method we don't have any default header content type.
     *
     * url : @NotNull
     * requestBody | requestBodyList : @NotNull (At least one can't be null)
     * requestHeaderList : (Can be or can't be null)
     */
    public String postDataBasic(@NonNull String url, String requestBody, ArrayList<NameValuePair> requestBodyList,

                                ArrayList<NameValuePair> requestHeaderList){

        HttpPost httpPost= new HttpPost();

        try {
            if (requestBody!=null && !requestBody.trim().equals("")) {
                httpPost.setURI(new URI(url));
                httpPost.setEntity(new ByteArrayEntity(requestBody.toString().getBytes("UTF8")));
                if(requestHeaderList!=null) {
                    for (int i = 0; i < requestHeaderList.size(); i++) {
                        httpPost.addHeader(requestHeaderList.get(i).getName(), requestHeaderList.get(i).getValue());
                    }
                }
                HttpResponse response = client.execute(httpPost);
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }else{
                httpPost.setURI(new URI(url));
                if(requestHeaderList!=null) {
                    for (int i = 0; i < requestHeaderList.size(); i++) {
                        httpPost.addHeader(requestHeaderList.get(i).getName(), requestHeaderList.get(i).getValue());
                    }
                }
                httpPost.setEntity(new UrlEncodedFormEntity(requestBodyList));
                HttpResponse response = client.execute(httpPost);
                HttpEntity httpentity = response.getEntity();
                InputStream is = httpentity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is),8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine())!= null) {
                    sb.append(line+"\n");
                }
                is.close();
                String result = sb.toString();
                result = result.replaceFirst("\"","").trim();
                if(result.endsWith("\""))
                    result = result.substring(0,result.lastIndexOf("\""));
                result = result.replace("\\\"","\"");
                DataUtil.sendLogsToLogger(DataPersistence.getInstance().getApplicationContext(),result);
                return result;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            DataUtil.getStackTrace(e.getCause());
        }
        return null;
    }



    /**
     *
     * @param url
     * @param requestBodyList
     * @param requestHeaderList
     * @return
     *
     * In this method url can't be null, requestBodyList can't be null, requestHeaderList can be null as per your requirement
     *
     * url : @NotNull
     * requestBodyList : @NotNull
     * requestHeaderList : (Can be or can't be null)
     */
    public String getData(@NonNull String url, @NonNull ArrayList<NameValuePair> requestBodyList, ArrayList<NameValuePair> requestHeaderList){

        try {
            String paramString = URLEncodedUtils.format(requestBodyList, "utf-8");
            url += "?" + paramString;
            DataUtil.sendLogsToLogger(DataPersistence.getInstance().getApplicationContext(),url);
            HttpGet httpget = new HttpGet(url);
            httpget.addHeader("Accept", "application/json");
            if(requestHeaderList!=null){
                for(int i=0;i<requestHeaderList.size();i++) {
                    httpget.addHeader(requestHeaderList.get(i).getName(),requestHeaderList.get(i).getValue());
                }
            }
            HttpResponse httpresponse = client.execute(httpget);
            HttpEntity httpentity = httpresponse.getEntity();
            InputStream is = httpentity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine())!= null) {
                sb.append(line+"\n");
            }
            is.close();
            return sb.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}