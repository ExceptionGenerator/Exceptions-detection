package com.shiv.exception.sqlite;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class SuperAdminChangeModel {
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
}
