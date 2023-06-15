package com.example.chatfirebase;

public class AwesomeMessage {

    public AwesomeMessage() {
    }

    public AwesomeMessage(String text, String name, String imagUrl) {
        this.text = text;
        this.name = name;
        this.imagUrl = imagUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }

    String text;
    String name ;
    String imagUrl;
}
