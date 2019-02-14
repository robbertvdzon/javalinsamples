package com.demobasicauth.domain;

import io.javalin.security.Role;

public enum UserRole implements Role {
    NONE, ROLE_ONE, ROLE_TWO, ROLE_THREE;
}