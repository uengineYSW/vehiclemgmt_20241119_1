package com.posco.assignoperation.s20a01.service;

import com.posco.assignoperation.s20a01.domain.carAssignment.CancelCarAssignmentCommand;
import com.posco.assignoperation.s20a01.domain.carAssignment.CarAssignment;
import com.posco.assignoperation.s20a01.domain.carAssignment.RequestCarAssignmentCommand;
import com.posco.assignoperation.s20a01.domain.carAssignment.UpdateCarAssignmentCommand;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class CarAssignmentController {

    private final CarAssignmentRepositoryService carAssignmentRepositoryService;

    @Autowired
    public CarAssignmentController(
        CarAssignmentRepositoryService carAssignmentRepositoryService
    ) {
        this.carAssignmentRepositoryService = carAssignmentRepositoryService;
    }

    @GetMapping(path = "/carAssignments")
    public ResponseEntity<List<CarAssignment>> findAll() {
        return ResponseEntity.ok(carAssignmentRepositoryService.findAll());
    }

    @PostMapping(path = "/carAssignments")
    public ResponseEntity<CarAssignment> create(
        @Valid @RequestBody RequestCarAssignmentCommand command
    ) {
        return ResponseEntity.ok(
            carAssignmentRepositoryService.create(command)
        );
    }

    @DeleteMapping(path = "carAssignments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carAssignmentRepositoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "carAssignments/{id}")
    public ResponseEntity<CarAssignment> update(
        @PathVariable Long id,
        @Valid @RequestBody UpdateCarAssignmentCommand command
    ) {
        return ResponseEntity.ok(
            carAssignmentRepositoryService.update(id, command)
        );
    }

    @GetMapping(path = "carAssignments/{id}")
    public ResponseEntity<CarAssignment> searchCarAssignment(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(
            carAssignmentRepositoryService.searchCarAssignment(id)
        );
    }
}
