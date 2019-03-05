package com.pe.ventas.back.dtos.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonDto {

    private JsonDto() {
    }

    public static Gson obtenerConvertidorJson(final boolean serializeNulls) {

        final GsonBuilder builder = new GsonBuilder().setPrettyPrinting();

        if (serializeNulls) {
            builder.serializeNulls();
        }

        return builder.create();
    }

    public static <T> T aJson(final String json, final Class<T> clazz) {
        return JsonDto.obtenerConvertidorJson().fromJson(json, clazz);
    }

    public static Gson obtenerConvertidorJson() {
        return obtenerConvertidorJson(false);
    }

    public static String aJson(final Object object) {
        return JsonDto.obtenerConvertidorJson().toJson(object);
    }

}
