package com.codingshuttle.kashif.week2Assignment.departmentAssignment.controllers;

import com.codingshuttle.kashif.week2Assignment.departmentAssignment.dto.DepartmentDTO;
import com.codingshuttle.kashif.week2Assignment.departmentAssignment.exceptions.DepartmentNotFound;
import com.codingshuttle.kashif.week2Assignment.departmentAssignment.services.DepartmentService;
import com.codingshuttle.kashif.week2Assignment.departmentAssignment.exceptions.DepartmentNotFound;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/department")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long departmentId){
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(departmentId);
        return departmentDTO
                .map(departmentDTO1 -> ResponseEntity.ok(departmentDTO1))
                .orElseThrow(() -> new DepartmentNotFound("Employee Not Found with id "+departmentId));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO inputDepartment){
        DepartmentDTO createdDepartment = departmentService.createNewDepartment(inputDepartment);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@PathVariable Long id,@RequestBody DepartmentDTO inputDepartment){
        return ResponseEntity.ok(departmentService.updateDepartmentById(id,inputDepartment));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long id){
        boolean gotDeleted = departmentService.deleteDepartmentById(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<DepartmentDTO> updatePartialDepartmentById(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        DepartmentDTO departmentDTO = departmentService.updatePartialDepartmentById(id,updates);
        if(departmentDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(departmentDTO);
    }

}
