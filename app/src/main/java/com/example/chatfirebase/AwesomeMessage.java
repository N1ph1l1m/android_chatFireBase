package com.example.chatfirebase;

public class AwesomeMessage {

    public AwesomeMessage() {
    }

    public AwesomeMessage(String text, String name, String imagUrl, String sender, String recipient) {
        this.text = text;
        this.name = name;
        this.imagUrl = imagUrl;
        this.sender = sender;
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
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

    private String text;
    private String name ;
    private String imagUrl;
    private String sender;
    private String recipient;

}
