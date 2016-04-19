package com.airdata.airdata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText userEdit,passEdit;
    Button loginButton,registerButton;
    ProgressDialog progressDialog;
    JSONParser parser = new JSONParser();
    JSONArray response;
    public String loginurl = "http://www.wikihands.com/Travel/login.php";

    String userString, passString, resUsername, resPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);

        userEdit= (EditText) findViewById(R.id.lusername);
        passEdit= (EditText) findViewById(R.id.lpassword);

        loginButton= (Button) findViewById(R.id.login);
        registerButton= (Button) findViewById(R.id.register);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id=v.getId();

        if (id==R.id.login)
        {
            userString = userEdit.getText().toString();
            passString = passEdit.getText().toString();
            if (userString.length() > 0 && passString.length() > 0) {
                new Login().execute();
            } else {
                Toast.makeText(LoginActivity.this, "Enter all the fields ", Toast.LENGTH_LONG).show();
            }
        }
        else if(id==R.id.register)
        {
            Intent in=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(in);
        }
    }

    public class Login extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Please wait....");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                List<NameValuePair> args = new ArrayList<>();
                args.add(new BasicNameValuePair("username", userString));

                JSONObject object = parser.makeHttpRequest(loginurl, "POST", args);
                response = object.getJSONArray("login");

                for (int i = 0; i < response.length(); i++) {
                    JSONObject c = response.getJSONObject(i);

                    resUsername = c.getString("username");
                    resPassword = c.getString("password");
                }

            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (userString.equals(resUsername) && passString.equals(resPassword)) {
                Intent in = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(in);
            } else {
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
            }
            progressDialog.dismiss();
        }
    }
}