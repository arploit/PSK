package infotech.psk.psk;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

/**
 * Created by arpesh on 5/3/18 2:51 AM PSK.
 */

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    private Context BackgroundWorkerContext;

    protected TextView MobileNo;

    private ProgressBar progressBar1;


    BackgroundWorker(Context ctx){
        BackgroundWorkerContext = ctx;

    }
    void Views( ProgressBar progressBar){
        this.progressBar1 = progressBar;
    }
    void textView(TextView textView){
        this.MobileNo = textView;
    }

    @Override
    protected String doInBackground(String... strings) {

        String Login_Url = "https://arploit.000webhostapp.com/login.php";

        if(strings[0].equals("Login")){
            String data = "";
            try {
                URL url = new URL(Login_Url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setFixedLengthStreamingMode(strings[1].getBytes().length);
                httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                httpURLConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");

                httpURLConnection.setRequestProperty("Connection", "close");
                httpURLConnection.connect();


                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                Log.d("JSP",wr.toString());
                wr.write(strings[1].getBytes());
                Log.d("JSP",wr.toString());
                wr.flush();


                InputStream in = httpURLConnection.getInputStream();

                InputStreamReader inputStreamReader = new InputStreamReader(in);
                int inputStreamData = inputStreamReader.read();
                while (inputStreamData != -1) {
                    char current = (char) inputStreamData;
                    inputStreamData = inputStreamReader.read();
                    data += current;
                    Log.d("Data", data);}
                wr.close();
                in.close();



            } catch (MalformedURLException e) {
                Log.d("JSon1",e.toString());
            } catch (IOException e) {
                Log.d("JSon2",e.toString());
            }
            return data;

        }
        if(strings[0].equals("register")){

            return getServerResponse(strings[1]);
        }


        return null;
    }


    private String getServerResponse(String json) {

        String SignIN_Url = "https://arploit.000webhostapp.com/register.php";
        System.out.print("USer values" + json);
        Log.d("JSP",SignIN_Url);
        String data ="";
        try {
            URL url = new URL(SignIN_Url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setFixedLengthStreamingMode(json.getBytes().length);
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            httpURLConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            httpURLConnection.connect();

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());

            wr.write(json.getBytes());

            wr.flush();


            InputStream in = httpURLConnection.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(in);

            int inputStreamData = inputStreamReader.read();

            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;
           }
            wr.close();
            in.close();



        } catch (MalformedURLException e) {
            Log.d("JSon1",e.toString());
        } catch (IOException e) {
            Log.d("JSon2",e.toString());
        }
        return data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressBar1.setVisibility(View.VISIBLE);

    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressBar1.setVisibility(View.INVISIBLE);
        // System.out.println(result);


        if (result == null) {
            Toast.makeText
                    (
                            BackgroundWorkerContext.getApplicationContext(),
                            "Failed....", Toast.LENGTH_SHORT
                    ).show();

        }
        else if (result.equals("Registration Failed") )
        {
            progressBar1.setVisibility(View.INVISIBLE);
            Toast.makeText
                    (
                            BackgroundWorkerContext.getApplicationContext(),
                            "Failed....", Toast.LENGTH_SHORT
                    ).show();

        }else if ( result.equals("Login Failed")){
            progressBar1.setVisibility(View.INVISIBLE);
            Toast.makeText
                    (
                            BackgroundWorkerContext.getApplicationContext(),
                            "Failed....", Toast.LENGTH_SHORT
                    ).show();
            BackgroundWorkerContext.startActivity(
                    new Intent
                            (
                                    BackgroundWorkerContext.getApplicationContext(),
                                    Registration.class
                            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

        else if (result.equals("Registration Successful"))
        {
            progressBar1.setVisibility(View.INVISIBLE);
            Toast.makeText
                    (BackgroundWorkerContext.getApplicationContext()
                            , result, Toast.LENGTH_SHORT).show();

            BackgroundWorkerContext.startActivity(
                    new Intent
                            (
                                    BackgroundWorkerContext.getApplicationContext(),
                                    LoginActivity.class
                            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

        else if (result.equals("Login Successful"))
        {

            progressBar1.setVisibility(View.INVISIBLE);
            Toast.makeText
                    (BackgroundWorkerContext.getApplicationContext()
                            , result, Toast.LENGTH_SHORT).show();
            BackgroundWorkerContext.startActivity(
                    new Intent
                            (
                                    BackgroundWorkerContext.getApplicationContext(),
                                    dietPlan.class
                            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));



            }

        else
        {

           Toast.makeText(

                   BackgroundWorkerContext.getApplicationContext(),result +" Register then",Toast.LENGTH_SHORT

           ).show();
            BackgroundWorkerContext.startActivity(
                    new Intent
                            (
                                    BackgroundWorkerContext.getApplicationContext(),
                                    Registration.class
                            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        }
    }




    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
