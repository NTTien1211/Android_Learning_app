package com.hqsoft.esales.doancuoiky;

import static android.graphics.Color.TRANSPARENT;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class FeedbackActivity extends AppCompatActivity {
    LottieAnimationView robot ;
    Button btnFeedback , send ;
    private TextView mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Follow Us");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#D2B4F4")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        anhxa();
        robot.animate().setDuration(1000).setStartDelay(5000);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Showdilalog_feedback(Gravity.CENTER);
            }
        });

    }

    private void Showdilalog_feedback(int gravity) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_feedback);
        Window  window = dialog.getWindow();

        if (window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
        Context context ;
        WindowManager.LayoutParams windowAtt = window.getAttributes();
        windowAtt.gravity = gravity;
        window.setAttributes(windowAtt);
        dialog.show();
        send = dialog.findViewById(R.id.btn_sendmail);
        mEditTextTo =dialog.findViewById(R.id.email_admin);
        mEditTextSubject = dialog.findViewById(R.id.title_email);
        mEditTextMessage = dialog.findViewById(R.id.email_content);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendmail();
            }
        });


    }

    private void sendmail() {
        String recipientList = mEditTextTo.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    private void anhxa() {
        robot = findViewById(R.id.robot_json);
        btnFeedback = findViewById(R.id.btnFeedback);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

}