package com.posco.standardmanagement.s20a01.domain.user;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateUserCommand {

    private String name;
    private String email;
}
