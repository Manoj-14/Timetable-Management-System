package com.example.timetablemanagementsystem;

public class ModClass {
    int sem;
    String subCode;
    String subName;
    String mon;
    String tue;
    String wed;
    String thu;
    String fri;
    String sat;


    public ModClass(int sem, String subCode, String subName, String mon, String tue, String wed, String thu, String fri, String sat) {
        this.sem = sem;
        this.subCode = subCode;
        this.subName = subName;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
    }

    public String getSem() {
        return String.valueOf(sem);
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getTue() {
        return tue;
    }

    public void setTue(String tue) {
        this.tue = tue;
    }

    public String getWed() {
        return wed;
    }

    public void setWed(String wed) {
        this.wed = wed;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public String getFri() {
        return fri;
    }

    public void setFri(String fri) {
        this.fri = fri;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }


}
