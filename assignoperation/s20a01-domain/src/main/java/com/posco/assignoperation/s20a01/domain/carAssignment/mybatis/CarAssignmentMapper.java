package com.posco.assignoperation.s20a01.domain.carAssignment.mybatis;

import com.posco.assignoperation.s20a01.domain.carAssignment.mybatis.SearchCarAssignmentMybatisEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarAssignmentMapper {
    SearchCarAssignmentMybatisEntity searchCarAssignment(Long id);
}
