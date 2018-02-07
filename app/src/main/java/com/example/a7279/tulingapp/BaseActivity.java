package com.example.a7279.tulingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.litepal.LitePal;

/**
 * Created by a7279 on 2017/11/19.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitiyCollector.add(this);
        LitePal.getDatabase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiyCollector.remove(this);
        HistoryActivity.adapter=null;
    }
}
