package com.seibels.integration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seibels.integration.document.render.IndexInfoItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Meh {
    public static void main(String[] args) {
        new Meh().execute();
    }

    private void execute() {
        String meh = "[{\"name\":\"Process Date\",\"value\":\"2018-05-30\"},{\"name\":\"Account Number\",\"value\":\"GSA00000001-LOCAL\"},{\"name\":\"Document SubType\",\"value\":\"QUOTE PROPOSAL\"},{\"name\":\"Description\",\"value\":\"Quote (Submission GSQ00000001-LOCAL [Version #1]) Eff 2018-05-25\"},{\"name\":\"Document Title\",\"value\":\"Quote (Submission GSQ00000001-LOCAL [Version #1]) Eff 2018-05-25\"},{\"name\":\"Document Type\",\"value\":\"POL - System Documents\"},{\"name\":\"Company Code\",\"value\":\"GPCIC\"},{\"name\":\"Document ID\",\"value\":\"GS-local-401\"},{\"name\":\"Quote Number\",\"value\":\"GSQ00000001-LOCAL\"},{\"name\":\"Process Type\",\"value\":\"Policy Administration\"}]";
        System.out.println("meh = " + meh);
        Type listType = new TypeToken<ArrayList<IndexInfoItem>>(){}.getType();
        List<IndexInfoItem> indexInfoItems = new Gson().fromJson(meh, listType);
        System.out.println("o = " + indexInfoItems);
        System.out.println("o = " + indexInfoItems.size());

    }
}
