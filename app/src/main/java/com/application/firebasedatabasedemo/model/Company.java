package com.application.firebasedatabasedemo.model;

import java.util.ArrayList;

/**
 * Created by Mindbowser on 5/31/2018.
 */

public class Company extends ArrayList<String> {

    String company_name;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
