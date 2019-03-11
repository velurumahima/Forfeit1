package com.ocr.traffic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;

    SharedPreferences spf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.et_userName);
        password = (EditText) findViewById(R.id.et_password);

        spf = getSharedPreferences("setup", 0);
        String uid = spf.getString("uid", null);
        if (uid != null) {


            Intent it = new Intent(LoginActivity.this, MainActivity.class);


            it.putExtra("user", uid);
            startActivity(it);
            finish();


        }
    }
    public void signin(View v)
    {
        final String un=username.getText().toString();
        final String ps=password.getText().toString();

        if(un.trim().length()<3)
            username.setError("Enter a valid User name");
        else
        if(ps.trim().length()<3)
            password.setError("Enter a valid password");
        else
        {

            String url=getResources().getString(R.string.host)+"userlogin.jsp";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.trim().equals("success"))
                            {
     SharedPreferences.Editor editor =spf.edit();
     editor.putString("uid",un);
     editor.commit();
                                Intent it=new Intent(LoginActivity.this,MainActivity.class);



                                it.putExtra("user",un);
                                startActivity(it);
                                finish();
                            }
                            else
                                Toast.makeText(LoginActivity.this,response, Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, "Error ! Try again later", Toast.LENGTH_SHORT).show();
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map map=new HashMap<String,String>();
                    {
                        map.put("username",un);
                        map.put("password",ps);
                        return  map;
                    }
                }
            };

            Volley.newRequestQueue(this).add(stringRequest);

        }
    }
}
