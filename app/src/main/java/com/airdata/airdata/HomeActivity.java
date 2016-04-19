package com.airdata.airdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    Button dataButton,musicButton,shareButton,uploadButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);
        dataButton= (Button) findViewById(R.id.data);
        musicButton= (Button) findViewById(R.id.music);
        shareButton= (Button) findViewById(R.id.share);
        uploadButton= (Button) findViewById(R.id.upload);

        dataButton.setOnClickListener(this);
        musicButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);
        uploadButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.data)
        {
            Intent in=new Intent(HomeActivity.this,DataActivity.class);
            startActivity(in);
        }
        else if(id==R.id.music)
        {
            Intent in=new Intent(HomeActivity.this,MusicActivity.class);
            startActivity(in);
        }
        else if(id==R.id.share)
        {
            Intent in=new Intent(HomeActivity.this,ShareActivity.class);
            startActivity(in);
        }
        else if(id==R.id.upload)
        {
            Intent in=new Intent(HomeActivity.this,UploadActivity.class);
            startActivity(in);
        }
    }
}