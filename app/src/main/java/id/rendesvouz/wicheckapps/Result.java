package id.rendesvouz.wicheckapps;

import android.content.Intent;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();
        TextView tvAnswer = findViewById(R.id.tv_answer);
        String answer = "";
        String answers = "";

        //1000 milliseconds = 1 second
        int milliseconds = 3000;


        if (bundle.getString("AnswerNo") != null) answer = bundle.getString("AnswerNo");
        else if (bundle.getString("AnswerYes") != null) answer = bundle.getString("AnswerYes");

        if (bundle.getString("AnswersNo") != null) answers = bundle.getString("AnswersNo");
        else if (bundle.getString("AnswersYes") != null) answers = bundle.getString("AnswersYes");

        tvAnswer.setText(answer);
        Toast.makeText(Result.this, "Going to the home page in " + milliseconds / 1000 + " seconds", Toast.LENGTH_SHORT).show();

        //insert into Record table
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(Result.this);
        databaseAccess.open();

        PassingColor passingColor = PassingColor.getInstance();
        String R = Integer.toString(passingColor.getRed());
        String G = Integer.toString(passingColor.getGreen());
        String B = Integer.toString(passingColor.getBlue());

        //insert date
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.US);
        Date date = new Date();
        String time = dateFormat.format(date);

        databaseAccess.addRecord(R, G, B, time, answers);
        databaseAccess.close();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, milliseconds);
    }

    public boolean shouldAllowBack(){
        return false;
    }
    public void doNothing(){
        finish();
        moveTaskToBack(true);
    }

    @Override
    public void onBackPressed() {
        if (!shouldAllowBack()) {
            doNothing();
        } else {
            super.onBackPressed();
        }
    }
}
