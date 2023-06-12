package com.example.meowaddopt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText fullname, email, password, confpassword;
    MaterialButton registerbutton;
    TextView loginlink;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname = findViewById(R.id.fullNameText);
        email = findViewById(R.id.emailLoginText);
        password = findViewById(R.id.passwordLoginText);
        confpassword = findViewById(R.id.retypePassword);
        registerbutton = findViewById(R.id.registerBtn);
        progressDialog = new ProgressDialog(RegisterActivity.this);
        loginlink = findViewById(R.id. toLoginPage);

        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sFullname = fullname.getText().toString();
                String sEmail = email.getText().toString();
                String sPassword = password.getText().toString();
                String sConfPassword = confpassword.getText().toString();

                if(!sFullname.equals("")){
                    if(!sEmail.equals("")){
                        if(!sPassword.equals("")) {
                            if (sPassword.equals(sConfPassword)) {
                                RegisterAction(sFullname, sEmail, sPassword);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "Password Can't be Null ", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Email Can't be Null ", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Fullname Can't be Null ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void RegisterAction(final String fullname, final String email, final String password){
        if(checkNetworkConnection()){
            progressDialog.setTitle("Please Wait");
            progressDialog.setMessage("Processing Your Registration");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DBContract.SERVER_REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.e("tagconvertstr", "["+response+"]");
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if(resp.equals("[{\"status\":\"OK\"}]")){
                                    Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                    Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(loginIntent);
                                }else{
                                    Toast.makeText(RegisterActivity.this, resp, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("fullname", fullname);
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };

            VolleyConnection.getInstance(RegisterActivity.this).addToRequesQue(stringRequest);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
                }
            }, 3000);
        }else{
            Toast.makeText(this, "No Network Available!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}