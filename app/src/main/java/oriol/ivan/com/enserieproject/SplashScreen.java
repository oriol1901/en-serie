package oriol.ivan.com.enserieproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    ProgressBar progressBarCircular;
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        progressBarCircular = (ProgressBar)findViewById(R.id.progressBarCircular);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Intent i = new Intent().setClass(
                        SplashScreen.this, AuthActivity.class);
                startActivity(i);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);

        new AsyncTask_load().execute();
    }

    public class AsyncTask_load extends AsyncTask<Void, Integer, Void> {
        int progreso;
        @Override
        protected void onPreExecute() {
            progreso = 0;
            progressBarCircular.setVisibility(View.VISIBLE);
        }
        @Override
        protected Void doInBackground(Void... params) {

            while(progreso < 1000){
                progreso++;
                publishProgress(progreso);
                SystemClock.sleep(28);
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {

            progressBarCircular.setProgress(values[0]);
        }
        @Override
        protected void onPostExecute(Void result) {
            progressBarCircular.setVisibility(View.INVISIBLE);
        }


    }
}
