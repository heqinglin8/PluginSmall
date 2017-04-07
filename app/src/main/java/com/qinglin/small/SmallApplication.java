package com.qinglin.small;

import android.app.Application;

import net.wequick.small.Small;

/**
 * 作者/author：何清林（Administrator）
 * 时间/time：2017-2017/3/24-12:32
 * 邮箱/email：237607591@qq.com
 */
public class SmallApplication extends Application {
    public SmallApplication() {
        Small.preSetUp(this);
    }
}
