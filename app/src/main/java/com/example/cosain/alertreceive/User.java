package com.example.cosain.alertreceive;

/**
 * Created by cosain on 4/19/2017.
 */

public class User {
    private String name;
    private String status;


    public User() {

    }

    public User(String name , String status){
           this.name = name;
            this.status = status;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status){
        this.status = status;

    }

    public String getName()
    {
        return name;
    }
    public String getStatus(){


        return status;
    }
}
