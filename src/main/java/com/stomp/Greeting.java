package com.stomp;

/**
 * Created by Catbat on 18.11.2016.
 */
public class Greeting {

    private String content;

    public Greeting(){

    }

    public Greeting(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
