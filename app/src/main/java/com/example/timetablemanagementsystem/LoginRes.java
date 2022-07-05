package com.example.timetablemanagementsystem;

import org.json.JSONArray;

public class LoginRes {

    private String usn;
    private String name,branch;
    private int sem;

    public String getBranch() {
        return branch;
    }

    public int getSem() {
        return sem;
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
    private String name,branch,data;
    private String[] sem;
//    private JSONArray data = new JSONArray();

    public String[] getSem() {
        return sem;
    }

    public String getData() {
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