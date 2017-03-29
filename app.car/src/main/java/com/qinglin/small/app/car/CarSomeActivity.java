package com.qinglin.small.app.car;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.wequick.small.Small;

public class CarSomeActivity extends AppCompatActivity implements View.OnClickListener {
        final String appname = "找车插件";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tv_dsc = (TextView) findViewById(R.id.tv_dsc);
        Button bt1 = (Button) findViewById(R.id.bt1);
        Button bt2 = (Button) findViewById(R.id.bt2);
        Button bt3 = (Button) findViewById(R.id.bt3);
        Uri uri = Small.getUri(this);
        if (uri != null) {
            tv_dsc.setText(uri.getQueryParameter("appname") + "第" + uri.getQueryParameter("count") + "次吊起了我");
        }



        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                count++;
                lauchToDealerPlgin();
                break;
            case R.id.bt2:
                count++;
                lauchToCarPlginBySchema();
                break;
            case R.id.bt3:
                count++;
                lauchToDealerPlginBySchema();
                break;
        }
    }

    private void lauchToDealerPlginBySchema() {
        String URI_INVOKE = "small://dealer/dealerlist";
        Uri uri = Uri.parse(URI_INVOKE).buildUpon()
                .appendQueryParameter("appname", appname)
                .appendQueryParameter("count",count+"").build();

        Intent intent = new Intent();
        intent.setData(uri);
        startActivity(intent);
    }

    private void lauchToCarPlginBySchema() {
        String URI_INVOKE = "small://car/carlist";
        Uri uri = Uri.parse(URI_INVOKE).buildUpon()
                .appendQueryParameter("appname", appname)
                .appendQueryParameter("count",count+ "").build();

        Intent intent = new Intent();
        intent.setData(uri);
        startActivity(intent);
    }

    int count = 0;
    public void lauchToDealerPlgin() {
        Small.setUp(this, new Small.OnCompleteListener() {
            @Override
            public void onComplete() {
                String uri = "dealer?appname=" + appname + "&count=" + count;
                Small.openUri(uri, CarSomeActivity.this);
            }
        });
    }

}
