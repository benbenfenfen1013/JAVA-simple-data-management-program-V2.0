package com.example.web.model;

public class Mission {
    private int id;
    private String name;
    private String country;
    private String date;
    private String result;

    public Mission(int id,String name,String country, String date,String result){
        this.id=id;
        this.name=name;
        this.country=country;
        this.date=date;
        this.result=result;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getCountry(){
        return country;
    }
    public String getDate(){
        return date;
    }
    public String getResult(){
        return result;
    }
}
