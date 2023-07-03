package com.example.assignment2.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Api(value = "Stock model information")
@Data
public class StockDto {

    @ApiModelProperty(value = "Stock id")
    private int id;

    @ApiModelProperty(value = "Product id")
    @NotNull(message = "Product ID should not be null")
    private Integer productId;

    @ApiModelProperty(value = "Stock quantity")
    @Min(value = 0, message = "Quantity should be a positive number")
    private Integer quantity;

    @ApiModelProperty(value = "Stock updated at")
    private LocalDateTime updatedAt;
}

