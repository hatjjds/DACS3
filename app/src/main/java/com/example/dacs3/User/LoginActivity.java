package com.example.dacs3.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dacs3.DetailActivity.DetailActivity;
import com.example.dacs3.MainActivity;
import com.example.dacs3.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    Button buttonLoginApp;
    TextView buttonRegistrar;
    EditText email, password;

    private static final String Json_URL="http://192.168.1.103/Json/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.tabselected)));
        getSupportActionBar().setTitle("Đăng nhập");

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        buttonRegistrar = findViewById(R.id.registrar);
        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(intent);
            }
        });

        buttonLoginApp = findViewById(R.id.btn_login_app);
        buttonLoginApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu trống", Toast.LENGTH_SHORT).show();
                } else {
                    checkAccount(Json_URL,email.getText().toString().trim(),password.getText().toString().trim());
                }
            }
        });
    }
    private void checkAccount (String Url,String email,String password){
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param=new HashMap<>();
                param.put("user",email);
                param.put("password",password);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}