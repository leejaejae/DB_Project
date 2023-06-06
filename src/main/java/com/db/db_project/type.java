package com.db.db_project;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.ims.ImsMmTelManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class type extends AppCompatActivity {

    TextView textfiled;
    EditText editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type);

        Button tohome = (Button) findViewById(R.id.tohome);
        Button tomap = (Button) findViewById(R.id.tomap);
        Button search_item = (Button) findViewById(R.id.search_item);
        editSearch = (EditText) findViewById(R.id.editSearch);

        tohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        tomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), map.class);
                startActivity(intent);
            }
        });

        search_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDB();
//                Intent intent = new Intent(getApplicationContext(), type_data.class);
//                intent.putExtra();
            }
        });
    }

    public void getDB() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//        String url = "http://localhost/dbdb.php";
                StringBuilder stringBuilder = new StringBuilder();

                String striem = editSearch.getText().toString(); // 요청변수 넣을 때마다 검색

//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url-> {

                try {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject jsonObject = new JSONObject(striem);

                    Log.d("sjdsjdksjd", String.valueOf(jsonObject));

                    for (int i = 0; i < jsonArray.length(); i++) {
//                    if(jsonArray != null){
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String img = jsonObject.getString("img");
                        String num = jsonObject.getString("num");
                        String title = jsonObject.getString("title");
                        String recyclee = jsonObject.getString("recyclee");
                        String kind = jsonObject.getString("kind");
                        String mark = jsonObject.getString("mark");
                        String howtouse = jsonObject.getString("howtouse");

//                    String img = jsonObject.getString(String.valueOf(i));
//                    String num = jsonObject.getString(String.valueOf(i));
//                    String title = jsonObject.getString(String.valueOf(i));
//                    String recyclee = jsonObject.getString(String.valueOf(i));
//                    String kind = jsonObject.getString(String.valueOf(i));
//                    String mark = jsonObject.getString(String.valueOf(i));
//                    String howtouse = jsonObject.getString(String.valueOf(i));

//                    Log.d("img", "몇 명? : " + number);
//                    Log.d("num", "몇 명? : " + number);
                        Log.d("title", "title: " + title);
//                    Log.d("recyclee", "몇 명? : " + number);
//                    Log.d("mark", "몇 명? : " + number);
//                    Log.d("howtouse", "몇 명? : " + number)


                        stringBuilder.append("쓰레기 이름: ").append(title);
//                    textfiled.setText(stringBuilder);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textfiled.setText(stringBuilder);
                return;
//        }, error -> Toast.makeText(this, "실패함. 인터넷 연결 확인", Toast.LENGTH_SHORT).show());
//
//        RequestQueue requestQueue = Volley.newRequestQueue(type.this);
//        requestQueue.add(stringRequest);
            }
        };
        get_Request get_request = new get_Request(responseListener);
        RequestQueue queue = Volley.newRequestQueue(type.this);
        queue.add(get_request);
    }
}



//
//    public void getDB() {
//        Response.Listener<String> responseListener = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
////                    URL url = new URL(urlBuilder.toString());
//
//                    JSONObject jsonObject = new JSONObject(response);
//                    Log.d("앙!", String.valueOf(jsonObject));
//
//                    boolean success = jsonObject.getBoolean("success");
//                    if (success) {
//                        int length = jsonObject.length();
//                        for (int i = 0; i <= length; i++) { // 있는 수만큼 반복
//                            String img = jsonObject.getString(String.valueOf(i));
//                            String num = jsonObject.getString(String.valueOf(i));
//                            String title = jsonObject.getString(String.valueOf(i));
//                            String recyclee = jsonObject.getString(String.valueOf(i));
//                            String kind = jsonObject.getString(String.valueOf(i));
//                            String mark = jsonObject.getString(String.valueOf(i));
//                            String howtouse = jsonObject.getString(String.valueOf(i));
//                        }
//                    } else {
////                        Toast.makeText(get"인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
////                    Toast.makeText(getContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
//                    return;
//                } catch (Exception e) {
//                    e.printStackTrace();
////                    Toast.makeText(getContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//        };
//
//        get_Request get_Request = new get_Request(responseListener);
//        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
//        queue.add(get_Request);
//    }
//
    class get_Request extends StringRequest {
        final static private String URL = "http://localhost/dbdb.php";
        private Map<String, String> map;

        public get_Request(Response.Listener<String>listener){
            super(URL, listener, null);

            map = new HashMap<>();
//        map.put("num", num+"");
        }
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
    }







//                        InputStream is = url.openStream();
//
//                        int fileSize = is.available();
//
//                        byte[] buffer = new byte[fileSize];
//                        is.read(buffer);
//                        is.close();
//
////                    json = new String(buffer, "UTF-8");
//                    } catch(Exception e){
//                        e.printStackTrace();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    String data() throws IOException, JSONException {
//        String str = editSearch.getText().toString(); // 동에 요청변수 넣을 때마다 검색

//        StringBuilder urlBuilder = new StringBuilder("http://localhost/dbdb.php"); /*URL*/
//        String json = "";
//        JSONObject jsonObject = new JSONObject(json);


//        try {
//            URL url = new URL(urlBuilder.toString());
//
//            JSONObject jsonObject = new JSONObject(json);
//            Log.d("앙!", String.valueOf(jsonObject));
//
//
//            InputStream is = url.openStream();
//
//            int fileSize = is.available();
//
//            byte[] buffer = new byte[fileSize];
//            is.read(buffer);
//            is.close();
//
//            json = new String(buffer, "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        return json;
//    }
//
//    private void jsonParsing(String json)
//    {
//        try{
//            JSONObject jsonObject = new JSONObject(json);
//
//            JSONArray itemArray = jsonObject.getJSONArray("Movies");
//
//            for(int i=0; i<itemArray.length(); i++)
//            {
//                JSONObject itemObject = itemArray.getJSONObject(i);
//
//                json_parsing items = new json_parsing();
//
//                items.setTitle(itemObject.getString("title"));
//                items.setHowtouse(itemObject.getString("howtouse"));
//                items.setImg(itemObject.getString("img"));
//                items.setKind(itemObject.getString("kind"));
//                items.setMark(itemObject.getString("mark"));
//                items.setNum(itemObject.getString("num"));
//                items.setRecyclee(itemObject.getString("recyclee"));
//
//                itemArray.add(items);
//            }
//        }catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }


//}