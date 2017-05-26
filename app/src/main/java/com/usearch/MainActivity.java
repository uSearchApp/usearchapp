package com.usearch;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.usearch.adapters.HistoryAdapter;
import com.usearch.models.Product;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnHistory;
    private RecyclerView historyRecylcer;
    private RelativeLayout warningEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historyRecylcer = (RecyclerView) findViewById(R.id.historyRecycler);
        btnHistory = (FloatingActionButton) findViewById(R.id.btnHistory);
        warningEmpty = (RelativeLayout) findViewById(R.id.warningEmpty);

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

        RealmResults<Product> products = getResults();

        if ( products.size() > 0){
            showHistory();
        }else{
            showWarningEmpty();
        }
    }

    private void showHistory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        historyRecylcer.setLayoutManager(linearLayoutManager);

        HistoryAdapter historyAdapter = new HistoryAdapter(this, getResults(), true);

        historyRecylcer.setAdapter(historyAdapter);

        historyRecylcer.setVisibility(View.VISIBLE);
        warningEmpty.setVisibility(View.GONE);
    }

    private void showWarningEmpty(){
        warningEmpty.setVisibility(View.VISIBLE);
        historyRecylcer.setVisibility(View.GONE);
    }

    private RealmResults<Product> getResults() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Product> query = realm.where(Product.class);
        return query.findAll();
    }

}
