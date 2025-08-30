package com.miron.profileservice.infrastructure.controller.dto;

import java.util.List;
import java.util.UUID;

public class ArrayRequestMapper {
    private List<UUID> listToMap;

    public ArrayRequestMapper(List<UUID> listToMap) {
        this.listToMap = listToMap;
    }

    public void mapStringToList(String payload) {
        payload = payload.replaceAll("\\s", "");
        payload = payload.substring(1, payload.length() - 1);
        String[] strings = payload.split(",");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].substring(strings[i].indexOf(':')+2, strings[i].length() - 2);
            listToMap.add(UUID.fromString(strings[i]));
        }
    }
}
