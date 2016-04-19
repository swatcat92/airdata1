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

public class RegisterActivity extends AppCompatActivity {
    EditText nameEdit,mobileEdit,emailEdit,usernameEdit,passwordEdit;
    Button submitButton;

    ProgressDialog progressDialog;
    JSONParser parser = new JSONParser();
    JSONArray response;
    public String registerurl = "http://www.wikihands.com/Travel/register.php";

    String nameString, mobileString, mailString, usernameString, passwordString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);
        nameEdit= (EditText) findViewById(R.id.name);
        mobileEdit= (EditText) findViewById(R.id.mobile);
        emailEdit= (EditText) findViewById(R.id.email);
        usernameEdit= (EditText) findViewById(R.id.username);
        passwordEdit= (EditText) findViewById(R.id.password);

        submitButton= (Button) findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameString = nameEdit.getText().toString();
                mobileString = mobileEdit.getText().toString();
                mailString = emailEdit.getText().toString();
                usernameString = usernameEdit.getText().toString();
                passwordString = passwordEdit.getText().toString();

                if (nameString.length() > 0 && mobileString.length() > 0 && mailString.length() > 0 && usernameString.length() > 0 && passwordString.length() > 0) {
                    new Register().execute();
                } else {
                    Toast.makeText(RegisterActivity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public class Register extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setMessage("Please wait....");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                List<NameValuePair> args = new ArrayList<>();
                args.add(new BasicNameValuePair("name",nameString));
                args.add(new BasicNameValuePair("phone",mobileString));
                args.add(new BasicNameValuePair("mail",mailString));
                args.add(new BasicNameValuePair("username",usernameString));
                args.add(new BasicNameValuePair("password",passwordString));

                JSONObject object = parser.makeHttpRequest(registerurl, "POST", args);


            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent in = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(in);
            progressDialog.dismiss();
        }

        }
    }
