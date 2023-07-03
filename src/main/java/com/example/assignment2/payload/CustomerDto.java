package com.example.assignment2.payload;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Api(value = "Customer model information")
@Data
public class CustomerDto {

    @ApiModelProperty(value = "Customer id")
    private int id;

    @ApiModelProperty(value = "Customer first name")
    @NotEmpty(message = "First name should not be null or empty")
    private String firstName;

    @ApiModelProperty(value = "Customer last name")
    @NotEmpty(message = "Last name should not be null or empty")
    private String lastName;

    @ApiModelProperty(value = "Customer birth date")
    @NotNull(message = "Birth date should not be null")
    private LocalDate bornAt;
}

