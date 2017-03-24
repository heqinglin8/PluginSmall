package com.qinglin.small.app.dealer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import net.wequick.small.Small;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_dics = (TextView) findViewById(R.id.tv_dics);

        Uri uri = Small.getUri(this);
        if (uri != null) {
            tv_dics.setText(uri.getQueryParameter("appname") + "第" + uri.getQueryParameter("count") + "次吊起了我");
        }


    }

}
