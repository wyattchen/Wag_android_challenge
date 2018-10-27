package com.example.jenniferhan.wyattchenapp;

public class Items { //item object that includes the gravatar, display_name, badges, and user_type
    private String gravatar;
    private String dp_name;
    private String badges;
    private String user_type;

    public Items(String gravatar, String dp_name, String badges, String user_type) {
        this.gravatar = gravatar;
        this.dp_name = dp_name;
        this.badges= badges;
        this.user_type= user_type;

    }

    public String getGravatar() {
        return gravatar;
    }

    public void setGravatar(String gravatar) {
        this.gravatar = gravatar;
    }

    public String getDp_name() {
        return dp_name;
    }

    public void setDp_name(String dp_name) {
        this.dp_name = dp_name;
    }

    public String getBadges() {
        return badges;
    }

    public void setBadges(String badgeGold) {
        this.badges = badges;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String badgeSilver) {
        this.user_type = user_type;
    }


}
