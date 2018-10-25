package com.example.root.upfiletransfer;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class PeerChooserActivity extends AppCompatActivity {

    EditText tvReceiver;
    Button bSendFinal;

    ArrayList<File> filesToBeSent ;

    public String username ;

    public String hue = "" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peer_chooser);

        tvReceiver = (EditText) findViewById(R.id.tvReceiver) ;
        bSendFinal = (Button) findViewById(R.id.bSendFinal) ;

        filesToBeSent = (ArrayList<File>) getIntent().getSerializableExtra("filesToBeSent");
        username = getIntent().getStringExtra("username");

        bSendFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = tvReceiver.getText().toString() ;
                for(File f : filesToBeSent){
                    String encode = null ;
                    try {

                        byte[] bytes = loadFile(f);
                        encode = new String(Base64.encodeBase64(bytes));

                        Log.d("Encode : " , encode) ;

//                        encode = "HELLO" ;
                        JSONObject jsonObject = new JSONObject() ;

                        jsonObject.put("name" , username);
                        jsonObject.put("sendto" , user);
                        jsonObject.put("filename" , f.getName());
                        jsonObject.put("data" , encode) ;
                        hue = jsonObject.toString(1) ;

                        Log.d("HUE" , hue) ;

                        new AsyncTask<Void,Void,String>(){

                        @Override
                        protected String doInBackground(Void... voids) {
                            return getServerResponse(hue);
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            Toast.makeText(getApplicationContext() , s , Toast.LENGTH_LONG).show();
                        }
                    }.execute();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private String getServerResponse(String json){
        HttpPut post  = new HttpPut("http://nipunsood.ooo/ft");
        try {
            StringEntity entity = new StringEntity(json);
            post.setEntity(entity);
            post.setHeader("Content-type" , "application/json");
            DefaultHttpClient client = new DefaultHttpClient();
            BasicResponseHandler handler  = new BasicResponseHandler();
            String ret = client.execute(post , handler);
            return ret ;
        } catch (UnsupportedEncodingException e) {
            Log.d("JWP" , e.toString());
        } catch (ClientProtocolException e) {
            Log.d("JWP" , e.toString());
        } catch (IOException e) {
            Log.d("JWP" , e.toString());
        }
        return "UNABLE TO CONTACT SERVER";
    }


    private String getMimeType(String path) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(path);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }
}
