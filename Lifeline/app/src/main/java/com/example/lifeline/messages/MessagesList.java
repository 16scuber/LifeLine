package com.example.lifeline.messages;

public class MessagesList {

    private String name, mobile, password, lastMessage;

    private int unseenMessages;

    public MessagesList(String name, String mobile, String password, String lastMessage, int unseenMessages) {
        this.name = name;
        this.mobile = mobile;
        this.password = password;
        this.lastMessage = lastMessage;
        this.unseenMessages = unseenMessages;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getUnseenMessages() {
        return unseenMessages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.mobile = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setUnseenMessages(int unseenMessages) {
        this.unseenMessages = unseenMessages;
    }
}
