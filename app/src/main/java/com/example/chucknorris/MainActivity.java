package com.example.chucknorris;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chucknorris.Helper.Helper;
import com.example.chucknorris.models.ChuckNorris;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private String API_URL = "http://api.icndb.com/jokes/random";
    Button btnJoke;
    TextView txtJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtJoke = (TextView)findViewById(R.id.txtJoke);
        btnJoke = (Button)findViewById(R.id.btnJoke);

        btnJoke.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AsyncTask<String,Void,String> asynctask = new AsyncTask<String, Void, String>() {


                    @Override
                    protected String doInBackground(String... params) {
                        Helper helper = new Helper();
                        Object result = helper.getHTTPData(API_URL);
                        ChuckNorris chuckNorris = new Gson().fromJson((JsonElement) result, ChuckNorris.class);
                       return chuckNorris.getValue().joke;
//                        return "Hello";
                    }

                    @Override
                    protected void onPostExecute(String s){
                        String debugS = s;

                        txtJoke.setText(s);
                        if(txtJoke.getVisibility()==View.INVISIBLE)
                            txtJoke.setVisibility(View.VISIBLE);


                    }

                };
                asynctask.execute();

            }


        });

        }
    }
