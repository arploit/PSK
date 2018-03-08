package infotech.psk.psk;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText inputUserName, inputPassword;
    private Button Login , SignIn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.Login_Progressbar);
        inputUserName = findViewById(R.id.Login_UserName);
        inputPassword = findViewById(R.id.Login_Password);
        Login = findViewById(R.id.loginButton);
        SignIn = findViewById(R.id.RegisterButton);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onLogin();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Registration.class));
            }
        });
    }

    private void onLogin() throws JSONException {
        JSONObject user = new JSONObject();
        String UserName = inputUserName.getText().toString().trim();
        String Password = inputPassword.getText().toString().trim();
        user.put("User_UserName",UserName);
        user.put("User_Password",Password);
        String type ="Login";
        BackgroundWorker ObjBackgroundWorker = new BackgroundWorker(getApplicationContext());
        ObjBackgroundWorker.Views(progressBar);
        ObjBackgroundWorker.execute(type ,user.toString());

    }
}
