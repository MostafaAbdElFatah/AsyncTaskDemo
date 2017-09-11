package com.mostafaabdel_fatah.asynctaskdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Mostafa AbdEl_Fatah on 9/6/2017.
 */
//                          AsyncTask<param,Process,Result>
public class MyTask extends AsyncTask<Void ,Integer,String> {
    Button button;
    Context context;
    TextView textView;
    ProgressDialog progressDialog;

    public MyTask(Context context,TextView textView ,Button button){
        this.context = context;
        this.button  = button;
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Download in Process...");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        synchronized (this){
            for (int i = 0 ; i < 10 ;i++){
                try {
                    wait(2000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Download Complete...";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setProgress(values[0]);
        textView.setText("Download in Process...");
    }

    @Override
    protected void onPostExecute(String result) {
        textView.setText(result);
        button.setEnabled(true);
        progressDialog.hide();
    }

}
