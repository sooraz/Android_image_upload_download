package com.example.volleymydemo;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;
import com.example.volleymydemo.AppContoller;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
//import com.android.volley.VolleyLog;
//import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText name,roll;
    ImageView img;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText)findViewById(R.id.editText);
        roll=(EditText)findViewById(R.id.editText2);
        img=(ImageView)findViewById(R.id.image_db);
    }
    String n,r;
    public void insert(View view) {
        n=name.getText().toString();
        r=roll.getText().toString();
        // Tag used to cancel the request
        //String tag_json_obj = "json_obj_req";
//        Log.d("sooraz", " insert "+n+" "+r);
        String url = "http://sooraz.000webhostapp.com/read.php";
        //Map<String, String> params = new HashMap<String, String>();
        //params.put("name", n);
        //params.put("roll", r);
        //JSONObject parameters = new JSONObject(params);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        Log.d("sooraz", " image read" );
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("sooraz", response/*getString("message")*/);
                            JSONObject temp = new JSONObject(response);
                            String img_bin=temp.getString("image");
                            Log.d("sooraz", "img_bin "+img_bin);
                            byte [] encodeByte= Base64.decode(img_bin.split(",")[1],Base64.DEFAULT);
                            Log.d("sooraz", "encodeByte "+encodeByte);
                            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                            Log.d("sooraz", "bitmap "+bitmap);
                            img.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("sooraz", "Error: " + error.getMessage());
                pDialog.hide();
            }

        }
        );

// Adding request to request queue
        AppContoller.getInstance().addToRequestQueue(jsonObjReq);
    }
}
