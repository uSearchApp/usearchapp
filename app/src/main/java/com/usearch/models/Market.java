package com.usearch.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Market extends RealmObject {

    @PrimaryKey
    private int id;

    @Required
    private String name;

    private String street;

    private String district;

    private String city;

    private String state;

    private String country;

    private double latitude;

    private double longitude;

}
