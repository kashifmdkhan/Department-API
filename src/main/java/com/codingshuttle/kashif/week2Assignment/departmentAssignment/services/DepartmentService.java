package com.codingshuttle.kashif.week2Assignment.departmentAssignment.services;

import com.codingshuttle.kashif.week2Assignment.departmentAssignment.dto.DepartmentDTO;
import com.codingshuttle.kashif.week2Assignment.departmentAssignment.entities.DepartmentEntity;
import com.codingshuttle.kashif.week2Assignment.departmentAssignment.exceptions.DepartmentNotFound;
import com.codingshuttle.kashif.week2Assignment.departmentAssignment.repositories.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public Optional<DepartmentDTO> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class));
    }

    public List<DepartmentDTO> getAllDepartment() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartment) {
        DepartmentEntity departmentEntity = modelMapper.map(inputDepartment,DepartmentEntity.class);
        departmentEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(departmentEntity,DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentById(Long id, DepartmentDTO inputDepartment) {
        isDepartmentExist(id);
        DepartmentEntity departmentEntity = modelMapper.map(inputDepartment,DepartmentEntity.class);
        departmentEntity.setId(id);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(departmentEntity);

        return modelMapper.map(savedDepartmentEntity,DepartmentDTO.class);
    }

    private boolean isDepartmentExist(Long id) {
        boolean isExist = departmentRepository.existsById(id);
        if(!isExist) throw new DepartmentNotFound("Department not Found with id " + id);
        return isExist;
    }

    public Boolean deleteDepartmentById(Long departmentId) {
        Boolean isExist = isDepartmentExist(departmentId);
        if(!isExist) return isExist;
        departmentRepository.deleteById(departmentId);
        return isExist;
    }

    public DepartmentDTO updatePartialDepartmentById(Long deptId, Map<String, Object> updates) {
        boolean isExist = isDepartmentExist(deptId);
        if(!isExist) return null;

        DepartmentEntity departmentEntity = departmentRepository.findById(deptId).get();

        updates.forEach((field,value) ->{
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(DepartmentEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,departmentEntity,value);
        });
        return modelMapper.map(departmentRepository.save(departmentEntity),DepartmentDTO.class);
    }
}
