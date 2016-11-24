package com.src.uncaughtexceptionhandler;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                Log.d(TAG, "uncaughtException");

                CrashExceptionHandler handler = new CrashExceptionHandler(getApplicationContext());
                handler.uncaughtException(thread, throwable);
            }
        });
    }

    public class CrashExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {

        private Context mContext;

        public CrashExceptionHandler(final Context context) {
            mContext = context;
        }

        @Override
        public void uncaughtException(Thread thread, Throwable throwable) {
            Log.d(TAG, Log.getStackTraceString(throwable));

            Intent intent = new Intent(mContext, CrashReportActivity.class);
            intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("error", Log.getStackTraceString(throwable));
            mContext.startActivity(intent);

            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(10);
        }
    }
}
