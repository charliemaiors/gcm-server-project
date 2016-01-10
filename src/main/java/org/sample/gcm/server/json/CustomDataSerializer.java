package org.sample.gcm.server.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;

/**
 * Created by Carlo on 29/11/2015.
 */
public class CustomDataSerializer implements JsonSerializer<CustomData>{
    @Override
    public JsonElement serialize(CustomData src, Type typeOfSrc, JsonSerializationContext context) {

        typeOfSrc = new TypeToken<LinkedHashMap<String,String>>(){}.getType();
        return context.serialize(src.getValues(),typeOfSrc);
    }
}
