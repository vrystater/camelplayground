package com.seibels.integration;

import com.google.gson.Gson;
import com.seibels.integration.document.render.IndexInfoItem;

import java.util.ArrayList;
import java.util.List;


public class AOS {
    public static void main(String[] args) {
        new AOS().execute();
    }

    private void execute() {
        String aos = new Gson().toJson(makeIndexInfoItems());
        System.out.println("aos = " + aos);


    }

    private List<IndexInfoItem> makeIndexInfoItems() {
        List<IndexInfoItem> stuff = new ArrayList<>();
        addInfoItem(stuff, "Account Number", "GSA00000001-LOCAL");
        addInfoItem(stuff, "Process Date", "2018-05-30");
        addInfoItem(stuff, "Document Type", "POL - System Documents");
        addInfoItem(stuff, "Document SubType", "QUOTE PROPOSAL");
        addInfoItem(stuff, "Description", "Quote (Submission GSQ00000001-LOCAL [Version #1]) Eff 2018-05-25");
        addInfoItem(stuff, "Document Title", "Quote (Submission GSQ00000001-LOCAL [Version #1]) Eff 2018-05-25");
        addInfoItem(stuff, "Company Code", "GPCIC");
        addInfoItem(stuff, "Document ID", "GS-local-401");
        addInfoItem(stuff, "Quote Number", "GSQ00000001-LOCAL");
        addInfoItem(stuff, "Process Type", "Policy Administration");
        return stuff;
    }

    private void addInfoItem(List<IndexInfoItem> stuff, String name, String value) {
        IndexInfoItem e = new IndexInfoItem();
        e.setName(name);
        e.setValue(value);
        stuff.add(e);
    }
}
