package com.codingshuttle.kashif.week2Assignment.departmentAssignment.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class DepartmentNotFound extends RuntimeException {

    public DepartmentNotFound(String message){
        super(message);
    }
}
