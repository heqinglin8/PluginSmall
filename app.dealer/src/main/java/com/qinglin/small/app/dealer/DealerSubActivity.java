package com.qinglin.small.app.dealer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import net.wequick.small.Small;

public class DealerSubActivity extends AppCompatActivity{
    TextView tv_dics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_sub);
        tv_dics = (TextView) findViewById(R.id.tv_dics);

        Uri uri = Small.getUri(this);
        if (uri != null) {
            tv_dics.setText(uri.getQueryParameter("appname") + "第" + uri.getQueryParameter("count") + "次吊起了我");
        }

        Intent intent = getIntent();
        if(intent!=null){
            handleSchemaJump();
        }

    }

    private void handleSchemaJump() {
        Uri uri = getIntent().getData();
        if(uri==null || uri.getHost() == null){
            return;
        }
        if(uri.getHost().equals("dealer")){
            tv_dics.setText(uri.getQueryParameter("appname") + "第" + uri.getQueryParameter("count") + "次吊起了我");
        }
    }

}
