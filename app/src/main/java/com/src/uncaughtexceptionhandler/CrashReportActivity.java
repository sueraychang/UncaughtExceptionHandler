package com.src.uncaughtexceptionhandler;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CrashReportActivity extends Activity {
    private static final String TAG = CrashReportActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_crash_report);

        TextView errorTextView = (TextView) findViewById(R.id.error);
        errorTextView.setText(getIntent().getStringExtra("error"));
    }
}
