package com.xathordroid.springapitest.resource.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RandomApi {
    
    private int count;
    private ArrayList<Entry> entries;
    
    public RandomApi() {}
    
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    
    public ArrayList<Entry> getEntries() {
        return this.entries;
    }
    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "API {" +
                "count:" + count +
                ", entries:" + entries + 
                "}";
    }
}
