package com.bwie.day_001.utils;

import android.os.AsyncTask;

import com.google.common.io.CharStreams;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
    private static final HttpUtils ourInstance = new HttpUtils();

    public static HttpUtils getInstance() {
        return ourInstance;
    }

    private HttpUtils() {

    }

    private NetCallBack netCallBack;
    public void setNetCallBack(NetCallBack netCallBack){
        this.netCallBack=netCallBack;
    }
    public interface NetCallBack{

        void onccess(String s);
    }

    public  void getSeriviceData(String url){

    }

    class LotaData extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(5000);
                connection.setRequestMethod("GET");
                while (connection.getResponseCode()==HttpURLConnection.HTTP_OK){

                    return CharStreams.toString(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(netCallBack!=null){
                netCallBack.onccess(s);
            }
        }
    }

}
