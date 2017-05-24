package com.example.marcelo.usearch10;

import android.app.Application;
import android.os.Bundle;

import com.example.marcelo.usearch10.helpers.VolleyQueue;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        VolleyQueue.init(this);

    }
}
