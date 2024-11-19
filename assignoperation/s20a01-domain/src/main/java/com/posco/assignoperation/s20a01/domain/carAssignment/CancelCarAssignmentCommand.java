package com.posco.assignoperation.s20a01.domain.carAssignment;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CancelCarAssignmentCommand {

    private Long id;
    private String cancellationReason;
}
