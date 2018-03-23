package infotech.psk.psk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class dietPlan extends AppCompatActivity {

        TextView diet_plan , dayTextView;
        Button fetch;
        ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);
        dayTextView = findViewById(R.id.DietPlan_DaytextView);
        diet_plan = findViewById(R.id.DietPlan_textView);
        progressBar = findViewById(R.id.DietPlan_ProgressBar);
        fetch = findViewById(R.id.DietPlan_FetchButton);



        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetch.setVisibility(View.GONE);
                JSONObject user = new JSONObject();
                dietPlanAsync dietPlanAsync = new dietPlanAsync(getApplicationContext());
                dietPlanAsync.textView(diet_plan,progressBar);
                Calendar calendar = Calendar.getInstance();
                dayTextView.setVisibility(View.VISIBLE);
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                switch (day) {

                    case Calendar.SUNDAY:

                        try {
                            dayTextView.setText("ON SUNDAY");
                            user.put("day","Sunday");

                            System.out.print("day" + user.toString());
                            dietPlanAsync.execute(user.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        break;

                    case Calendar.MONDAY:

                        try {
                            dayTextView.setText("ON MONDAY");
                            user.put("day","Monday");
                            System.out.print("day" + user.toString());
                            dietPlanAsync.execute(user.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        break;
                        // Current day is Monday

                    case Calendar.TUESDAY:

                        try {
                            dayTextView.setText("ON TUESDAY");
                            user.put("day","Tuesday");

                            System.out.print("day" + user.toString());
                            dietPlanAsync.execute(user.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        break;
                        // etc.

                    case Calendar.WEDNESDAY:

                        try {
                            dayTextView.setText("ON WEDNESDAY");
                            user.put("day","Wednesday");

                            System.out.print("day" + user.toString());
                            dietPlanAsync.execute(user.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        break;

                    case Calendar.THURSDAY:

                        try {
                            dayTextView.setText("ON THURSDAY");
                            user.put("day","Thursday");

                            System.out.print("day" + user.toString());
                            dietPlanAsync.execute(user.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        break;

                    case Calendar.FRIDAY:

                        try {
                            dayTextView.setText("ON FRIDAY");
                            user.put("day","Friday");

                            System.out.print("day" + user.toString());
                            dietPlanAsync.execute(user.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        break;

                    case Calendar.SATURDAY:

                        try {
                            dayTextView.setText("ON SATURDAY");
                            user.put("day","Saturday");

                            System.out.print("day" + user.toString());
                            dietPlanAsync.execute(user.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        break;
                    }
                }

        });
    }
}
