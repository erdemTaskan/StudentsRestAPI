package com.students.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoStudentsIU {
    @NotEmpty(message = "This field cannot be left blank")
    @Size(min = 3, max = 11, message = "Firstname field must be between 3 and 11 characters")
    private String firstname;

    @Size(min = 2, max = 16, message = "Lastname field must be between 2 and 16 characters")
    private String lastname;

    private Date birtOfdate;



}
