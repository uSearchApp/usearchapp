package com.example.marcelo.usearch10;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnHistory;
    private RecyclerView historyRecylcer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historyRecylcer = (RecyclerView) findViewById(R.id.historyRecycler);
        btnHistory = (FloatingActionButton) findViewById(R.id.btnHistory);

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, History.class);
                startActivity(intent);
            }
        });

        btnHistory.setImageDrawable(
                new IconicsDrawable(this, FontAwesome.Icon.faw_history)
                        .sizeDp(20).color(Color.WHITE));

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager
                .beginTransaction()
                .replace(R.id.container, new SurfaceOCR())
                .commit();
    }

}
