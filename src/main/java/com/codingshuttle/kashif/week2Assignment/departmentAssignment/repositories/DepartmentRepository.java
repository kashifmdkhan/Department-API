package com.codingshuttle.kashif.week2Assignment.departmentAssignment.repositories;

import com.codingshuttle.kashif.week2Assignment.departmentAssignment.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
