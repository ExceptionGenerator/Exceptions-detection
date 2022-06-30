package com.shiv.exception.sqlite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credential {
    private int userId;
    private String roles;
    private boolean isRoleActive;
    private String userName;
    private String password;
}
