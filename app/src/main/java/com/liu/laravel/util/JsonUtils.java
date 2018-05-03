package com.liu.laravel.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.liu.laravel.bean.jsons.FeatureBean;
import com.liu.laravel.bean.jsons.MapBean;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * created by liuxuhui on 2018/5/3  下午3:45
 */
public class JsonUtils {

    public static Gson getMapGson (){
        Gson gson = new GsonBuilder().registerTypeAdapter(MapBean.class, new MapJsonAdapter())
                .create();
        return gson;
    }
    /**
     * 动态解析map数据
     */
    public static class MapJsonAdapter implements JsonDeserializer<MapBean> {

        @Override
        public MapBean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Gson gson = new Gson();
            List<FeatureBean> featureBeanList = new ArrayList<>();
            Set<Map.Entry<String, JsonElement>> entries = json.getAsJsonObject().entrySet();
            for(Map.Entry<String, JsonElement> entry : entries) {
                if("data".equals(entry.getKey())) {
                    JsonObject jsonObject = entry.getValue().getAsJsonObject();
                    JsonElement future = jsonObject.get("future");
                    Set<Map.Entry<String, JsonElement>> dataEntrys = future.getAsJsonObject().entrySet();
                    for(Map.Entry<String, JsonElement> dataEntry : dataEntrys) {
                        JsonElement futureJson = dataEntry.getValue();
                        featureBeanList.add(gson.fromJson(futureJson, FeatureBean.class));
                    }
                    break;
                }
            }
            return new MapBean(featureBeanList);
        }
    }
}
