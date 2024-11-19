package com.posco.assignoperation.s20a01.domain.vehiclePerformance;

import com.posco.assignoperation.s20a01.domain.vehiclePerformance.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "vehiclePerformances",
    path = "vehiclePerformances"
)
public interface VehiclePerformanceRepository
    extends JpaRepository<VehiclePerformance, String> {
    //List<VehiclePerformance> searchVehiclePerformance
}
