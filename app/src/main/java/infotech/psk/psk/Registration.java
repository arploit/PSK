package infotech.psk.psk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;

public class Registration extends AppCompatActivity {

    private EditText  InputPassword ,InputUserName ;
    private Button Register;
    private ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        progressBar = findViewById(R.id.Register_Progressbar);
        InputUserName = findViewById(R.id.Register_userName);
        InputPassword = findViewById(R.id.Register_password);
        Register = findViewById(R.id.Register_registerButton);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sendDataToServer();}
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void sendDataToServer() throws JSONException {
        String type = "register";

        final String Json = FormatDataASJSon();
        BackgroundWorker ObjBackgroundWorker = new BackgroundWorker(getApplicationContext());
        ObjBackgroundWorker.Views(progressBar);
        ObjBackgroundWorker.execute(type,Json);

    }

    private String FormatDataASJSon() throws JSONException {
        final JSONObject user = new JSONObject();
        String UserName = InputUserName.getText().toString().trim();
        String Password = InputPassword.getText().toString().trim();
        user.put("User_UserName",UserName);
        user.put("User_Password",Password);
        Log.d("JSp",user.toString(1));
        return user.toString(1);
    }
}
