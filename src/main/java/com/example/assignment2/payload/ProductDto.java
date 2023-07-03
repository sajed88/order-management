package com.example.assignment2.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Api(value = "Product model information")
@Data
public class ProductDto {

    @ApiModelProperty(value = "Product id")
    private int id;

    @ApiModelProperty(value = "Product slug")
    private String slug;

    @ApiModelProperty(value = "Product name")
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @ApiModelProperty(value = "Product reference")
    @NotEmpty(message = "Reference should not be null or empty")
    private String reference;

    @ApiModelProperty(value = "Product price")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price should be greater than 0")
    private BigDecimal price;

    @ApiModelProperty(value = "Product VAT")
    @DecimalMin(value = "0.0", inclusive = false, message = "VAT should be greater than 0")
    private BigDecimal vat;

    @ApiModelProperty(value = "Product stockable")
    @NotNull(message = "Stockable should not be null")
    private Boolean stockable;
}
