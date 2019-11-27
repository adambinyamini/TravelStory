package com.example.ghost.travreldiary.Models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.example.ghost.travreldiary.Activities.MyTripsActivity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {


    Context ctx;

    public BackgroundTask(Context ctx){
        this.ctx = ctx;

    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://192.168.1.28/webapp/register.php";
        String login_url = "http://192.168.1.28/webapp/login.php";
        String addTrip_url = "http://192.168.1.28/webapp/addTrip.php";
        String method = params [0];
        if (method.equals("register")){

            String name = params[1];
            String user_name = params[2];
            String user_pass = params[3];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(user_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                is.close();
                return "Registration Success....";
            } catch (MalformedURLException e) {
                Log.d("My Error",e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("My Error",e.getMessage());
                e.printStackTrace();
            }


        }else if(method.equals("login")){
            String login_name = params [1];
            String login_pass = params [2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null){
                    response += line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;


            } catch (MalformedURLException e) {
                Log.d("My Error",e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if (method.equals("addTrip")){

            String nameOfTrip = params[1];
            String fromDate = params[2];
            String toDate = params[3];
            ;
            try {
                URL url = new URL(addTrip_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream ost = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(ost,"UTF-8"));
                String data = URLEncoder.encode("nameOfTrip","UTF-8")+"="+URLEncoder.encode(nameOfTrip,"UTF-8")+"&"+
                        URLEncoder.encode("fromDate","UTF-8")+"="+URLEncoder.encode(fromDate,"UTF-8")+"&"+
                        URLEncoder.encode("toDate","UTF-8")+"="+URLEncoder.encode(toDate,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                ost.close();
                InputStream is = httpURLConnection.getInputStream();
                is.close();
                return "Add Trip Success....";
            } catch (MalformedURLException ex) {
                Log.d("Error1",ex.getMessage());
                ex.printStackTrace();
            } catch (IOException ex) {
                Log.d("Error",ex.getMessage());
                ex.printStackTrace();
            }}

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(ctx, MyTripsActivity.class);
        if (result.equals("Add Trip Success....")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            ctx.startActivity(intent);

        } else if (result.equals("Registration Success....")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            ctx.startActivity(intent);
        }

    }




}
