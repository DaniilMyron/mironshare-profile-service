package com.miron.profileservice.domain.usecases;

import java.util.UUID;

public interface CheckPassword{
    boolean execute(UUID id, String password);
}
