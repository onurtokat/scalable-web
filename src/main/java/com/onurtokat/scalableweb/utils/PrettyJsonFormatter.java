package com.onurtokat.scalableweb.utils;

import com.google.gson.*;

/**
 * PrettyJsonFormatter provides pretty json text with whitespace
 * using Gson
 *
 * @author onurtokat
 */
public class PrettyJsonFormatter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * getPrettyFormat method provides pretty formatted JSON String
     *
     * @param jsonString JSON string before pretty format
     * @return pretty formatted JSON String
     */
    public static String getPrettyFormat(String jsonString) {
        JsonParser parser = new JsonParser();
        if (jsonString.startsWith("[")) {
            JsonArray jsonArray = new JsonParser().parse(jsonString).getAsJsonArray();
            return gson.toJson(jsonArray);
        } else {
            JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();
            return gson.toJson(jsonObject);
        }
    }
}
