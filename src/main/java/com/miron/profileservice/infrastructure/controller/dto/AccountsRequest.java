package com.miron.profileservice.infrastructure.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountsRequest {
    private List<UUID> usersId = new ArrayList<>();
    private ArrayRequestMapper arrayRequestMapper = new ArrayRequestMapper(usersId);
    public AccountsRequest(String httpBody) {
        arrayRequestMapper.mapStringToList(httpBody);
    }

    public List<UUID> getUsersId() {
        return usersId;
    }
}
