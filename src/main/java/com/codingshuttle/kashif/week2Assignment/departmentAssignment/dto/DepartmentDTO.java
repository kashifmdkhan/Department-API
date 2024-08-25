package com.codingshuttle.kashif.week2Assignment.departmentAssignment.dto;

import com.codingshuttle.kashif.week2Assignment.departmentAssignment.annotations.PasswordValidationAnnotation;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class DepartmentDTO {
    private Long id;

    @NotBlank(message = "Title can not be Blank")
    private String title;

    @AssertTrue(message = "Department should be active")
    private Boolean isActive;

    @PastOrPresent(message = "Dept can creation date can not be in future")
    private LocalDate createdAt;

    @NotNull(message = "A dept can not exist without HOD")
    private String deptHod;

    @Min(value = 100000, message = "Budget can not be less than 100000")
    @Max(value=1000000,message = "Budget can not be more than 1000000")
    private Integer deptBudget;

    @Email(message = "email should be valid")
    private String email;

    @PasswordValidationAnnotation
    private String password;

    public DepartmentDTO(){}
    public DepartmentDTO(Long id, String title, Boolean isActive, LocalDate createdAt,String deptHod,Integer deptBudget,
                         String email,String password) {
        this.id = id;
        this.title = title;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.deptHod = deptHod;
        this.deptBudget = deptBudget;
        this.email = email;
        this.password  = password;
    }

}
