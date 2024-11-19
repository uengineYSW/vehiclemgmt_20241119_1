package com.posco.standardmanagement.s20a01.service;

import com.posco.standardmanagement.s20a01.domain.vehicle.DeleteVehicleCommand;
import com.posco.standardmanagement.s20a01.domain.vehicle.RegisterVehicleCommand;
import com.posco.standardmanagement.s20a01.domain.vehicle.UpdateVehicleDetailsCommand;
import com.posco.standardmanagement.s20a01.domain.vehicle.UpdateVehicleStatusCommand;
import com.posco.standardmanagement.s20a01.domain.vehicle.Vehicle;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class VehicleController {

    private final VehicleRepositoryService vehicleRepositoryService;

    @Autowired
    public VehicleController(
        VehicleRepositoryService vehicleRepositoryService
    ) {
        this.vehicleRepositoryService = vehicleRepositoryService;
    }

    @GetMapping(path = "/vehicles")
    public ResponseEntity<List<Vehicle>> findAll() {
        return ResponseEntity.ok(vehicleRepositoryService.findAll());
    }

    @PostMapping(path = "/vehicles")
    public ResponseEntity<Vehicle> create(
        @Valid @RequestBody RegisterVehicleCommand command
    ) {
        return ResponseEntity.ok(vehicleRepositoryService.create(command));
    }

    @PostMapping(path = "vehicles/{id}/updateVehicleStatus")
    public ResponseEntity<Vehicle> updateVehicleStatus(
        @PathVariable("id") Long id,
        @Valid @RequestBody UpdateVehicleStatusCommand command
    ) {
        Vehicle vehicle = vehicleRepositoryService.findById(id);

        // 도메인 포트 메서드 직접 호출
        vehicle.updateVehicleStatus(command);

        return ResponseEntity.ok(vehicleRepositoryService.save(vehicle));
    }

    @PatchMapping(path = "vehicles/{id}")
    public ResponseEntity<Vehicle> update(
        @PathVariable Long id,
        @Valid @RequestBody UpdateVehicleDetailsCommand command
    ) {
        return ResponseEntity.ok(vehicleRepositoryService.update(id, command));
    }

    @DeleteMapping(path = "vehicles/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleRepositoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
