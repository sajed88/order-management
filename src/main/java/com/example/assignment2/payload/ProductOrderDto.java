package com.example.assignment2.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Api(value = "ProductOrder model information")
@Data
public class ProductOrderDto {

    @ApiModelProperty(value = "Product id")
    @NotNull(message = "Product ID should not be null")
    private Integer productId;

    @ApiModelProperty(value = "Order id")
    @NotNull(message = "Order ID should not be null")
    private Integer orderId;

    @ApiModelProperty(value = "Product quantity")
    @Min(value = 0, message = "Quantity should be a positive number")
    private Integer quantity;

    @ApiModelProperty(value = "Product price")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price should be greater than 0")
    private BigDecimal price;

    @ApiModelProperty(value = "Product VAT")
    @DecimalMin(value = "0.0", inclusive = false, message = "VAT should be greater than 0")
    private BigDecimal vat;
}

