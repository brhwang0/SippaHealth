package com.example.sippahealth;

public class DailyWisdomItem {

    private String dailyWisdomContent;
    private String dailyWisdomActivity;

    public DailyWisdomItem() {
        this.dailyWisdomContent = "";
        this.dailyWisdomActivity = "";
    }

    public DailyWisdomItem(String dailyWisdomContent, String dailyWisdomActivity) {
        this.dailyWisdomContent = dailyWisdomContent;
        this.dailyWisdomActivity = dailyWisdomActivity;
    }

    public String getDailyWisdomContent() { return dailyWisdomContent; }
    public String getDailyWisdomActivity() { return dailyWisdomActivity; }
}
