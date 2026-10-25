package com.qys.im.pojo;

public class Msg {
    private String illness_id;
    private String sendUserName;
    private String receiveUserName;
    private String sendName;
    private String receiveName;
    private String sendText;
    private String updateTime;
    private String createTime;
    private String chatmState;
    private int TextType;

    public Msg(String illness_id, String sendUserName, String receiveUserName, String sendName, String receiveName, String sendText, String updateTime, String createTime, String chatmState, int textType) {
        this.illness_id = illness_id;
        this.sendUserName = sendUserName;
        this.receiveUserName = receiveUserName;
        this.sendName = sendName;
        this.receiveName = receiveName;
        this.sendText = sendText;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.chatmState = chatmState;
        TextType = textType;
    }

    public String getIllness_id() {
        return illness_id;
    }

    public void setIllness_id(String illness_id) {
        this.illness_id = illness_id;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getSendText() {
        return sendText;
    }

    public void setSendText(String sendText) {
        this.sendText = sendText;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getChatmState() {
        return chatmState;
    }

    public void setChatmState(String chatmState) {
        this.chatmState = chatmState;
    }

    public int getTextType() {
        return TextType;
    }

    public void setTextType(int textType) {
        TextType = textType;
    }
}