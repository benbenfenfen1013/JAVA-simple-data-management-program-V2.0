package com.example.web.model;

public class Mission {
    private Integer id;
    private String name;
    private String country;
    private String date;
    private String result;

    public Mission(){
        
    }

    public Mission(Integer id,String name,String country, String date,String result){
        this.id=id;
        this.name=name;
        this.country=country;
        this.date=date;
        this.result=result;
    }

    public Integer getId(){
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
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult(){
        return result;
    }
}
