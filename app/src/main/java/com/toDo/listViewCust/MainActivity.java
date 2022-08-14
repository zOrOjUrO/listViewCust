package com.toDo.listViewCust;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        listView = findViewById(R.id.list);
        List<setData> data;
        data = new ArrayList<>();
        data.add(new setData(getString(R.string.title_listitems),
                getString(R.string.description),
                getString(R.string.testImageUrl)));
        data.add(new setData(getString(R.string.title_listitems),
                getString(R.string.description),
                "https://i.annihil.us/u/prod/marvel/i/mg/8/f0/60edc638b0b16/clean.jpg"));
        data.add(new setData(getString(R.string.title_listitems),
                getString(R.string.description),
                "https://c.tenor.com/r8yYCCn6w0YAAAAC/escape-yelena-belova.gif"));



        listAdapter adapter = new listAdapter(this, R.layout.list_items, data);
        listView.setAdapter(adapter);
        listView.refreshDrawableState();

    }
}