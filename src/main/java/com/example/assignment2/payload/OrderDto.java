package com.example.assignment2.payload;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Api(value = "Order model information")
@Data
public class OrderDto {

    @ApiModelProperty(value = "Order id")
    private int id;

    @ApiModelProperty(value = "Customer id")
    @NotNull(message = "Customer ID should not be null")
    private Integer customerId;

    @ApiModelProperty(value = "Order placed at")
    private LocalDateTime orderedAt;
}

