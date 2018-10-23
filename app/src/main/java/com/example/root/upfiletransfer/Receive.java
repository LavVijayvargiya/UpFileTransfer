package com.example.root.upfiletransfer;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Receive extends AppCompatActivity {

    ListView lv ;
    public final String url1 = "http://nipunsood.ooo/fp/";
    public String username ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        lv = (ListView) findViewById(R.id.lv);
        username = getIntent().getStringExtra("username");
        Toast.makeText(getApplicationContext(), username, Toast.LENGTH_LONG).show();
        String url = url1 + username ;
        new JSONTask().execute(url);

    }

    public class JSONTask extends AsyncTask<String , String , List<ListContent> > {

        @Override
        protected void onPostExecute(final List<ListContent> lists) {
            super.onPostExecute(lists);
            ArrayAdapter adapter = new ArrayAdapter(Receive.this , android.R.layout.simple_list_item_1
             , lists) ;

            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }

        @Override
        protected List<ListContent> doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                Log.d("LAV" , finalJson) ;


//                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = new JSONArray(finalJson);
//                Log.d("PARENT" , parentArray.toString()) ;
                List<ListContent> ret = new ArrayList<ListContent>();
                Gson gson = new Gson();


                for(int i = 0 ; i < parentArray.length() ; i++){
                    JSONArray inner = parentArray.optJSONArray(i) ;
                    Log.d("inner" , inner.toString()) ;
                    ListContent inn = new ListContent();
                    inn.setName(inner.getString(0));
                    Log.d("inner1" , inner.getString(0)) ;

                    inn.setFilename(inner.getString(1));
                    Log.d("inner2" , inner.getString(1)) ;
                    ret.add(inn) ;
//                    JSONArray o = null;
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                        o = new JSONArray(parentArray.getJSONArray(i));
//                        Log.d("OO" , o.toString(1));
//                    }
////                    ListContent inn = gson.fromJson( o.toString() , ListContent.class) ;
//                    ListContent inn = new ListContent();
//                    inn.setName(o.getString(0));
//                    inn.setFilename(o.getString(1));
//                    ret.add(inn) ;
//                    Log.d("HELLO" , inn.getFilename()) ;
                }
                return ret ;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("LOL" , " hmm ");
//            Toast.makeText(getApplicationContext(), "LOL!", Toast.LENGTH_LONG).show();

            return null;
        }
    }
}
