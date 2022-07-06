package com.example.timetablemanagementsystem;

import org.json.JSONArray;

import java.util.ArrayList;

public class LoginRes {

    private String usn;
    private String name,branch;
    private String sem;
    private int[] ttlSem;
    private String[]  subCode , subName , mon ,tue ,wed ,thr , fri, sat;

    public String getBranch() {
        return branch;
    }

    public String getSem() {
        return sem;
    }

    public int[] getTtlSem() {
        return ttlSem;
    }

    public String[] getSubCode() {
        return subCode;
    }

    public String[] getSubName() {
        return subName;
    }

    public String[] getMon() {
        return mon;
    }

    public String[] getTue() {
        return tue;
    }

    public String[] getWed() {
        return wed;
    }

    public String[] getThr() {
        return thr;
    }

    public String[] getFri() {
        return fri;
    }

    public String[] getSat() {
        return sat;
    }

    public String getUsn() {
        return usn;
    }

    public String getName() {
        return name;
    }
}

class FacLogin{
    private String fid;
    private String name,branch;
    private String[] sem, subCode , subName , mon ,tue ,wed ,thr , fri, sat;
    private int[] ttlSem;
    private ArrayList data;
//    private JSONArray data = new JSONArray();

    public String[] getSubCode() {
        return subCode;
    }
    public String[] getSem() {
        return sem;
    }

    public String[] getSubName() {
        return subName;
    }

    public String[] getMon() {
        return mon;
    }

    public String[] getTue() {
        return tue;
    }

    public String[] getWed() {
        return wed;
    }

    public String[] getThr() {
        return thr;
    }

    public String[] getFri() {
        return fri;
    }

    public String[] getSat() {
        return sat;
    }



    public int[] getTtlSem() {
        return ttlSem;
    }

    public ArrayList getData() {
        return data;
    }

    public String getFid() {
        return fid;
    }

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }


}