package com.jawx.android.bakingapp.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
    private int dataCount=0;
    private JSONArray jsonArray , ingredientsArray , stepsArray;
    private final static String KEY_NAME="name";
    private final static String KEY_INGREDIENTS_LIST="ingredients";
    private final static String KEY_INGREDIENTS_QUANTITY="quantity";
    private final static String KEY_INGREDIENTS_MEASURE="measure";
    private final static String KEY_INGREDIENT="ingredient";
    private final static String KEY_STEPS="steps";
    private final static String KEY_STEPS_SHORT_DESCRIPTION="shortDescription";
    private final static String KEY_STEPS_DESCRIPTION="description";
    private final static String KEY_STEPS_VIDEO_URL="videoURL";
    private final static String KEY_STEPS_THUMBNAIL_URL="thumbnailURL";

    public JsonUtils(String JSON_String) throws JSONException {
        jsonArray = new JSONArray(JSON_String);
        dataCount = jsonArray.length();
    }
    public int getDataCount(){
        return dataCount;
    }
    public String getName(int position) throws JSONException {
        JSONObject object = new JSONObject(jsonArray.getString(position));
        return object.getString(KEY_NAME);
    }
    public String getIngredients(int position) throws JSONException {
        JSONObject object = new JSONObject(jsonArray.getString(position));
        ingredientsArray = object.getJSONArray(KEY_INGREDIENTS_LIST);
        String indeg="";
        for(int i=0;i<ingredientsArray.length();i++){
            object = new JSONObject(ingredientsArray.getString(i));
            indeg += object.getString(KEY_INGREDIENTS_QUANTITY)+ " "+ object.getString(KEY_INGREDIENTS_MEASURE) + " of "+object.getString(KEY_INGREDIENT)+"\n";
        }
        return indeg;
    }
    public String[] getShortStepsList(int position) throws JSONException {
        JSONObject object = new JSONObject(jsonArray.getString(position));
        stepsArray = object.getJSONArray(KEY_STEPS);
        String [] steps = new String[stepsArray.length()];
        for (int i=0; i < stepsArray.length();i++){
            object = new JSONObject(stepsArray.getString(i));
            steps[i]= object.getString(KEY_STEPS_SHORT_DESCRIPTION);
        }
        return steps;
    }
    public String getStepDescription(int position) throws JSONException {
        JSONObject object = new JSONObject(stepsArray.getString(position));
        String desc = object.getString(KEY_STEPS_DESCRIPTION);
        return  desc;
    }
    public String getVideoURL(int position) throws JSONException {
        JSONObject object = new JSONObject(stepsArray.getString(position));
        String URL = object.getString(KEY_STEPS_VIDEO_URL);
        if (URL.equals("") || URL==null)
             URL = object.getString(KEY_STEPS_THUMBNAIL_URL);
        return  URL;
    }
}