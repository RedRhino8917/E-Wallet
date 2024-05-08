package com.saurabh.UserService.request;

import com.saurabh.UserService.customAnnotation.AgeLimit;
import com.saurabh.UserService.model.User;
import com.saurabh.Utils.UserIdentifier;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String name;
    private String email;
    @NotBlank
    private String phoneNo;
    private String address;
    private String password;
    @AgeLimit(minimumAge = 16, message = "your age should not be lesser than 16")
    private String dob;
    private UserIdentifier userIdentifier;

    private String userIdentifierValue;

    public User toUser() {
        return User.builder().
                name(this.name).
                email(this.email).
                phoneNo(this.phoneNo).
                address(this.address).
                password(this.password).
                userIdentifier(this.userIdentifier).
                userIdentifierValue(this.userIdentifierValue).
                build();
    }
}
