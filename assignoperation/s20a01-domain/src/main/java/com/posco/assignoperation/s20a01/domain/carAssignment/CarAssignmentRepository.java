package com.posco.assignoperation.s20a01.domain.carAssignment;

import com.posco.assignoperation.s20a01.domain.carAssignment.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "carAssignments",
    path = "carAssignments"
)
public interface CarAssignmentRepository
    extends JpaRepository<CarAssignment, Long> {
    //List<CarAssignment> searchCarAssignment
}
