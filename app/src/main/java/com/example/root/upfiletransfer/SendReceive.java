package com.example.root.upfiletransfer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;


import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendReceive extends AppCompatActivity {

    public String hue = "";
    public URL url = new URL("http://nipunsood.ooo/fp");
    ImageView send , receive;

    public String username;


    public SendReceive() throws MalformedURLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_receive);
        username = getIntent().getStringExtra("username");
        send = (ImageView) findViewById(R.id.send);
        receive = (ImageView) findViewById(R.id.receive);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SendReceive.this , DisplayFilesActivity.class) ;
                startActivity(i) ;
            }
        });

        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SendReceive.this , Receive.class);
                i.putExtra("username" , username);
                startActivity(i);
            }
        });

    }



//    ProgressDialog progress;
//
////    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
//        switch (requestCode){
//            case 7 :
//                if(resultCode == RESULT_OK){
//                    String path = data.getData().getPath();
//                    Log.d("HELLO" , path);
//                    Uri u = data.getData();
//                    String temp = u.toString();
//                    Log.d("CHODU", temp);
////                    File file = new File("/storage/emulated/0/Download/Screen-Shot-2015-03-16-at-2.12.33-PM-e1426533205934.png");
////                    byte[] filedata;
//
//
////                    try {
////                        Log.d("Lav", "yo 1");
//////                        FileInputStream fis = new FileInputStream(file);
////                        filedata = new byte[(int) file.length()];
////                        Log.d("Lav", "yo 2");
//////                        fis.read(filedata);
////                        BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
////                        Log.d("Lav", "yo 3");
////                        buf.read(filedata, 0, filedata.length);
////                        Log.d("Lav", "yo 4");
////                        buf.close();
////                        Log.d("Lav", "yo 5");
////                        Log.d("Lav", filedata.toString());
////                    }
////                        catch(IOException e){
////                        e.printStackTrace();
////                        Log.d("Lav", e.getStackTrace()[0].toString());
////                    }
////                    Log.d("Lav", "yo?");
//                    //////                  List<String> text = null;
////                    try {
////                        text = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                    try {
////                        filedata = text.getBytes("UTF-8");
////                    }catch(UnsupportedEncodingException e){
////                        e.printStackTrace();
////                    }
////                    String base64 = Base64.encodeToString(filedata, Base64.DEFAULT);
//
//                    progress = new ProgressDialog(SendReceive.this);
//                    progress.setTitle("Uploading");
//                    progress.setMessage("Please wait...");
//                    progress.show();
//
////                    Thread t = new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////                            File f = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
////                            String content_type = getMimeType(f.getPath());
////                            String file_path = f.getAbsolutePath();
////                            OkHttpClient client = new OkHttpClient();
////                            RequestBody file_body = RequestBody.create(MediaType.parse(content_type),f);
////
////                            RequestBody request_body = new MultipartBody.Builder()
////                                    .setType(MultipartBody.FORM)
////                                    .addFormDataPart("type",content_type)
////                                    .addFormDataPart("uploaded_file",file_path.substring(file_path.lastIndexOf("/")+1), file_body)
////                                    .build();
////
////                            Request request = new Request.Builder()
////                                    .url("http://nipunsood.ooo/fp")
////                                    .post(request_body)
////                                    .build();
////
////                            try {
////                                Response response = client.newCall(request).execute();
////
////                                if(!response.isSuccessful()){
////                                    throw new IOException("Error : "+response);
////                                }
////
////                                progress.dismiss();
////
////                            } catch (IOException e) {
////                                e.printStackTrace();
////                            }
////                        }
////                    });
////                    t.start();
//                    String tosend = "HELLO WORLD!";
//                    byte[] lol = new byte[0];
//                    try {
//                        lol = temp.getBytes("UTF-8");
//                    }catch (UnsupportedEncodingException e){
//                        e.printStackTrace();
//                    }
//                    String base64 = Base64.encodeToString(lol,Base64.DEFAULT);
//                    JSONObject json = new JSONObject();
//                    try{
//                        json.put("name" , tosend);
//                        json.put("sendto" , "l");
//                        json.put("filename" , "hmmmm");
//                        json.put("data" , base64);
//                        hue = json.toString(1);
////                        Toast.makeText(getApplicationContext() , hue , Toast.LENGTH_LONG).show();
//                    }catch (JSONException e){
//                        e.printStackTrace();
//                    }
//
//                    new AsyncTask<Void,Void,String>(){
//
//                        @Override
//                        protected String doInBackground(Void... voids) {
//                            return getServerResponse(hue);
//                        }
//
//                        @Override
//                        protected void onPostExecute(String s) {
//                            progress.dismiss();
//                            Toast.makeText(getApplicationContext() , s , Toast.LENGTH_LONG).show();
//                        }
//                    }.execute();
//
//
//
//
////                    HttpURLConnection httpCon = null;
////                    try {
////                        httpCon = (HttpURLConnection) url.openConnection();
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                    httpCon.setDoOutput(true);
////                    try {
////                        httpCon.setRequestMethod("PUT");
////                    } catch (ProtocolException e) {
////                        e.printStackTrace();
////                    }
////                    OutputStreamWriter out = null;
////                    try {
////                        out = new OutputStreamWriter(
////                                httpCon.getOutputStream());
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                    try {
////                        out.write(base64);
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                    try {
////                        out.close();
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                    Toast.makeText(getApplicationContext(),path , Toast.LENGTH_LONG).show();
//                }
//        }
//    }
//
//    private String getServerResponse(String json){
//        HttpPut post  = new HttpPut("http://nipunsood.ooo/ft");
//        try {
//            StringEntity entity = new StringEntity(json);
//            post.setEntity(entity);
//            post.setHeader("Content-type" , "application/json");
//            DefaultHttpClient client = new DefaultHttpClient();
//            BasicResponseHandler handler  = new BasicResponseHandler();
//            String ret = client.execute(post , handler);
//            return ret ;
//        } catch (UnsupportedEncodingException e) {
//            Log.d("JWP" , e.toString());
//        } catch (ClientProtocolException e) {
//            Log.d("JWP" , e.toString());
//        } catch (IOException e) {
//            Log.d("JWP" , e.toString());
//        }
//        return "UNABLE TO CONTACT SERVER";
//    }
//    private String getMimeType(String path) {
//
//        String extension = MimeTypeMap.getFileExtensionFromUrl(path);
//
//        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
//    }
}
