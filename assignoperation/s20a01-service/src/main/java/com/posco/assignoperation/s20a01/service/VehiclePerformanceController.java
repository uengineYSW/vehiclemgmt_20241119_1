package com.posco.assignoperation.s20a01.service;

import com.posco.assignoperation.s20a01.domain.vehiclePerformance.DeletePerformanceCommand;
import com.posco.assignoperation.s20a01.domain.vehiclePerformance.ModifyPerformanceCommand;
import com.posco.assignoperation.s20a01.domain.vehiclePerformance.RegisterDrivingLogCommand;
import com.posco.assignoperation.s20a01.domain.vehiclePerformance.RegisterPerformanceCommand;
import com.posco.assignoperation.s20a01.domain.vehiclePerformance.VehiclePerformance;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class VehiclePerformanceController {

    private final VehiclePerformanceRepositoryService vehiclePerformanceRepositoryService;

    @Autowired
    public VehiclePerformanceController(
        VehiclePerformanceRepositoryService vehiclePerformanceRepositoryService
    ) {
        this.vehiclePerformanceRepositoryService =
            vehiclePerformanceRepositoryService;
    }

    @GetMapping(path = "/vehiclePerformances")
    public ResponseEntity<List<VehiclePerformance>> findAll() {
        return ResponseEntity.ok(vehiclePerformanceRepositoryService.findAll());
    }

    @PostMapping(path = "/vehiclePerformances")
    public ResponseEntity<VehiclePerformance> create(
        @Valid @RequestBody RegisterPerformanceCommand command
    ) {
        return ResponseEntity.ok(
            vehiclePerformanceRepositoryService.create(command)
        );
    }

    @PatchMapping(path = "vehiclePerformances/{registrationId}")
    public ResponseEntity<VehiclePerformance> update(
        @PathVariable String registrationId,
        @Valid @RequestBody ModifyPerformanceCommand command
    ) {
        return ResponseEntity.ok(
            vehiclePerformanceRepositoryService.update(registrationId, command)
        );
    }

    @DeleteMapping(path = "vehiclePerformances/{registrationId}")
    public ResponseEntity<Void> delete(@PathVariable String registrationId) {
        vehiclePerformanceRepositoryService.delete(registrationId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(
        path = "vehiclePerformances/{registrationId}/registerDrivingLog"
    )
    public ResponseEntity<VehiclePerformance> registerDrivingLog(
        @PathVariable("registrationId") String registrationId,
        @Valid @RequestBody RegisterDrivingLogCommand command
    ) {
        VehiclePerformance vehiclePerformance = vehiclePerformanceRepositoryService.findById(
            registrationId
        );

        // 도메인 포트 메서드 직접 호출
        vehiclePerformance.registerDrivingLog(command);

        return ResponseEntity.ok(
            vehiclePerformanceRepositoryService.save(vehiclePerformance)
        );
    }

    @GetMapping(path = "vehiclePerformances/{registrationId}")
    public ResponseEntity<VehiclePerformance> searchVehiclePerformance(
        @PathVariable String registrationId
    ) {
        return ResponseEntity.ok(
            vehiclePerformanceRepositoryService.searchVehiclePerformance(
                registrationId
            )
        );
    }
}
