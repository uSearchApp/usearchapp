package com.usearch.models;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Product extends RealmObject {

    @PrimaryKey
    private int id;

    private String name;

    private float price;

    private String supermarcadoName;

}
