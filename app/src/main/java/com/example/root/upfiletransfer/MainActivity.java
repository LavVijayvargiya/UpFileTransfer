package com.example.root.upfiletransfer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


//    OkHttpClient client = new OkHttpClient();
    private OkHttpClient okHttpClient;
    private Request request;
    private Session session;
    public String url= "http://13.233.85.191:5000/fp/lav";
    Button b,b2;
    EditText t;
    public static final String PREFS_NAME = "LoginPrefs";
    public String st;
    EditText username;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new Session(this);
        if(!session.loggedIn()){
            logout();
        }else{
            startActivity(new Intent(getApplicationContext(),SendReceive.class));
            finish();
        }
    }

    private void logout() {
        session.setLoggedIn(false);
        finish();
        startActivity(new Intent(MainActivity.this , LoginActivity.class));
    }
}
