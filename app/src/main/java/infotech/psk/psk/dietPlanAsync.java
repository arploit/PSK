package infotech.psk.psk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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

/**
 * Created by arpesh on 5/3/18 10:58 PM PSK.
 */

public class dietPlanAsync extends AsyncTask<String,Void,String> {
    @SuppressLint("StaticFieldLeak")
    protected Context context;
    @SuppressLint("StaticFieldLeak")
    protected TextView textView;
    @SuppressLint("StaticFieldLeak")
    private ProgressBar progressBar1;


    dietPlanAsync(Context ctx){
        context = ctx;

    }

    void textView(TextView textView, ProgressBar progressBar){
        this.textView = textView;
        this.progressBar1 = progressBar;
    }


    @Override
    protected void onPreExecute() {

        progressBar1.setVisibility(View.VISIBLE);
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String URL = "https://arploit.000webhostapp.com/dietPlan.php";
        String data = "";
        String diet_plan = "";
        try {
            URL url = new URL(URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setFixedLengthStreamingMode(strings[0].getBytes().length);
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            Log.d("JSP",httpURLConnection.getRequestProperties().toString());
            httpURLConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            Log.d("JSP",httpURLConnection.getRequestProperties().toString());
            httpURLConnection.setRequestProperty("Connection", "close");
            httpURLConnection.connect();

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            Log.d("JSP",wr.toString());
            wr.write(strings[0].getBytes());
            Log.d("JSP",wr.toString());
            wr.flush();


            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            int inputStreamData = inputStreamReader.read();

            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;
                System.out.print("Diet plan received" + data);
            }
            JSONArray jsonarray = new JSONArray(data);
            JSONObject jsonobject = jsonarray.getJSONObject(0);
            diet_plan = jsonobject.getString("diet_plan");

            wr.close();
            in.close();



        } catch (MalformedURLException e) {
            Log.d("JSon1",e.toString());
        } catch (IOException e) {
            Log.d("JSon2",e.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return diet_plan;




    }

    @Override
    protected void onPostExecute(String s) {
        progressBar1.setVisibility(View.GONE);
        textView.setText(s);
    }
}
