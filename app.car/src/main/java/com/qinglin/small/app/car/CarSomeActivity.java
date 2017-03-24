package com.qinglin.small.app.car;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.wequick.small.Small;

public class CarSomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tv_dsc = (TextView) findViewById(R.id.tv_dsc);
        Uri uri = Small.getUri(this);
        if (uri != null) {
            tv_dsc.setText(uri.getQueryParameter("appname") + "第" + uri.getQueryParameter("count") + "次吊起了我");
        }


        Button bt1 = (Button) findViewById(R.id.bt1);

        bt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                lauchToDealerPlgin();
                break;
        }
    }
    int count = 0;
    public void lauchToDealerPlgin() {
        final String appname = "找车插件";
        Small.setUp(this, new Small.OnCompleteListener() {
            @Override
            public void onComplete() {
                count++;
                String uri = "dealer?appname=" + appname + "&count=" + count;
                Small.openUri(uri, CarSomeActivity.this);
            }
        });
    }

}
