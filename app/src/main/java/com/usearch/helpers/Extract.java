package com.usearch.helpers;

import android.util.Log;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extract {

    private final static Pattern name = Pattern.compile("([A-Z ]{4,}[A-Z]{2,})");
    private final static Pattern price = Pattern.compile("(\\d+[,\\.]{1}[0-9]{1,2})");

    private final static int COUNT = 8;

    private ArrayList<String> testsName;
    private ArrayList<String> testsPrice;

    public Extract(){
        testsName = new ArrayList<>();
        testsPrice = new ArrayList<>();
    }

    public void addName(String name){
        if ( testsName.size() < COUNT ) {
            testsName.add(name);
        }
    }

    public void addPrice(String price){
        if (testsPrice.size() < COUNT ) {
            testsPrice.add(price);
        }
    }

    public boolean fully(){
        return testsPrice.size() == COUNT || testsPrice.size() == COUNT;
    }

    public String getName(){
        return releaseTest(testsName);
    }

    public String getPrice(){
        return releaseTest(testsPrice);
    }

    public String releaseTest(ArrayList<String> target){

        Hashtable<String, Integer> reach = new Hashtable<>();

        String name = "";
        int count = 0;

        for ( int i = 0, len = target.size(); i < len; i++){
            String key = target.get(i);
            int value;

            if ( reach.contains(key) ){
                value = reach.get(key);
                reach.put(key, value + 1);
            }else{
                value = 1;
                reach.put(key, 1);
            }

            if ( ! name.equals(key) && value > count ){
                name = key;
                count = value;
            }else if(name.equals(key)){
                count = value;
            }
        }

        return name;
    }

    public void clear(){
        testsName.clear();
        testsPrice.clear();
    }

    public static String testPrice(String data){
        Matcher matcher = price.matcher(data);

        if (matcher.find()){
            if ( matcher.groupCount() > 1 ){
                float minPrice = 9999999.0f;
                String rPrice = "";

                for ( int i =0, len = matcher.groupCount(); i < len; i++){
                    String price = matcher.group(i);
                    float fPrice = Float.parseFloat(price);

                    Log.e("price f", price);
                    Log.e("sadas", "asd=-------------------------------------");
                    if ( fPrice < minPrice ){
                        minPrice = fPrice;
                        rPrice = price;
                    }
                }

                return rPrice;
            }else {
                return matcher.group();
            }
        }

        return "";
    }

    public static String testName(String data){
        Matcher nameMatcher = name.matcher(data);

        if (nameMatcher.find()){
            return nameMatcher.group();
        }

        return "";
    }

}
