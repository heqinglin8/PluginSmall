package com.qinglin.small.app.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qinglin.small.app.main.R;
import com.qinglin.small.lib.business.utils.NetUtil;
import com.qinglin.small.lib.business.utils.StringUtil;

import net.wequick.small.Small;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int count = 0;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkNet();
        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(this);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setOnClickListener(this);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv3.setOnClickListener(this);
    }

    private void checkNet() {
        if (!NetUtil.CheckNetState()) {
            Toast.makeText(this, "无网络", Toast.LENGTH_SHORT).show();
        } else {
            if (NetUtil.isWifi())
                Toast.makeText(this, "WIFI", Toast.LENGTH_SHORT).show();
            if (NetUtil.isMobile())
                Toast.makeText(this, "手机网络", Toast.LENGTH_SHORT).show();
        }
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                lauchToCarPlgin();
                break;
            case R.id.tv2:
                Toast.makeText(MainActivity.this, "hello kugou!", Toast.LENGTH_SHORT).show();
                lauchToMusicMain();
                break;
            case R.id.tv3:
                lauchToSupperTextView();
                break;
        }
    }

    public void lauchToCarPlgin() {
        final String appname = "主插件";
        Small.setUp(this, new Small.OnCompleteListener() {
            @Override
            public void onComplete() {
                count++;
                String uri = "car/some?appname=" + appname + "&count=" + count;
                Small.openUri(uri, MainActivity.this);
            }
        });
    }

    public void lauchToMusicMain() {
        Small.setUp(this, new Small.OnCompleteListener() {
            @Override
            public void onComplete() {
                String uri = "main/musicmain";
                Small.openUri(uri, MainActivity.this);
            }
        });
    }

    public void lauchToSupperTextView() {
        Small.setUp(this, new Small.OnCompleteListener() {
            @Override
            public void onComplete() {
                String uri = "main/supertext";
                Small.openUri(uri, MainActivity.this);
            }
        });
    }

}
