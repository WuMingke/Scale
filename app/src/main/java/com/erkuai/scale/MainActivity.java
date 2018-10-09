package com.erkuai.scale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bitmap_scale;
    private Button edit_scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bitmap_scale = (Button) findViewById(R.id.bitmap_scale);
        edit_scale = (Button) findViewById(R.id.edit_scale);
        bitmap_scale.setOnClickListener(this);
        edit_scale.setOnClickListener(this);


        File file = new File("");
        OkHttpUtils.post().addFile("","",file)
                .addParams("name","caicai")
                .addParams("sex","man");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bitmap_scale:
                startActivity(new Intent(this,BitmapScaleActivity.class));
                break;
            case R.id.edit_scale:
                startActivity(new Intent(this,EditTextScaleActivity.class));
                break;
        }
    }
}
