package com.posco.assignoperation.s20a01.domain.vehiclePerformance.mybatis;

import com.posco.assignoperation.s20a01.domain.vehiclePerformance.mybatis.SearchVehiclePerformanceMybatisEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VehiclePerformanceMapper {
    SearchVehiclePerformanceMybatisEntity searchVehiclePerformance(String id);
}
