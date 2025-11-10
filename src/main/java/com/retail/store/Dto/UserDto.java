package com.retail.store.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @Size(max = 255)
    private String userName;

    @Size(max = 255)
    private String userFirstName;

    @Size(max = 255)
    private String userLastName;

    @Size(max = 255)
    private String password;

    @NotNull
    @Size(max = 255)
    private String email;

    private Boolean enabled;

    private Boolean credentialsNonExpired;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private List<String> roles;
}
