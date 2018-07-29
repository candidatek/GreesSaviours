package com.example.shrihari.greessaviours;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private String mStringName, mStringPhone ;
    EditText mEditTextname , mEditTextphone ;
    Button mButton ;
    private final String URL_PAGE = "http://172.17.0.121/green/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getValues();
        buttonClicked();



    }


    private void buttonClicked(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void validateData() {
        mStringName = mEditTextname.getText().toString();
        mStringPhone = mEditTextphone.getText().toString().trim();
        if(mStringPhone.length()!=10){
            Log.d("chk","Please Enter Valid Number");
            Toast.makeText(Register.this,"Please Enter Valid Number",Toast.LENGTH_SHORT).show();
        }else{
            Log.d("chk","name "+mStringName+" phone "+mStringPhone);

            callToServer();
        }
    }

    private void saveMemberInfo() {
        SharedPreferences share = getSharedPreferences("memberInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("name",mStringName);
        editor.putString("phone",mStringPhone);
        editor.apply();
    }

    private void callToServer() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PAGE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    String success = json.getString("success");

                    if((success.equals("1"))||success.equals("-1")) {
                        String res = json.getString("message");
                        Log.d("chk", " " + res);
                        Toast.makeText(Register.this, res, Toast.LENGTH_SHORT).show();
                        saveMemberInfo();
                        displayData();
                        nextIntent();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("chk",""+e.toString());
                    Toast.makeText(Register.this , "Return -"+e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("chk","Erroe -"+error.toString());
                        Toast.makeText(Register.this , "Error -"+error.toString(),Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> param = new HashMap<>();
                param.put("name",mStringName);
                param.put("phone",mStringPhone);
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void displayData() {
        SharedPreferences sharedPref = getSharedPreferences("memberInfo2",MODE_PRIVATE);
       String memberName = sharedPref.getString("name","");
       String phone = sharedPref.getString("phone","");
        Log.d("chk","name "+memberName+" phone "+phone);
        Toast.makeText(Register.this , memberName+" - "+phone,Toast.LENGTH_LONG).show();
    }

    private void nextIntent() {
        Intent intent =  new Intent(Register.this , MainActivity.class);
        startActivity(intent);
    }

    private void getValues() {
        mEditTextname = findViewById(R.id.nameX);
        mEditTextphone = findViewById(R.id.phoneX);
        mButton = findViewById(R.id.regButton);

    }
}
