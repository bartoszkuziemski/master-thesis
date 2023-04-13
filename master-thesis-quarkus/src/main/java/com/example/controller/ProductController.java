package com.example.controller;

import com.example.dto.ProductDto;
import com.example.model.enums.CategoryType;
import com.example.model.enums.ConditionType;
import com.example.model.enums.OfferType;
import com.example.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @Path("/all")
    public Response getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return Response.ok(products).build();
    }

    @GET
    public Response getProducts(
            @QueryParam("name") String name,
            @QueryParam("priceMin") Double priceMin,
            @QueryParam("priceMax") Double priceMax,
            @QueryParam("ratingMin") Double ratingMin,
            @QueryParam("categoryType") Set<CategoryType> categoryType,
            @QueryParam("conditionType") Set<ConditionType> conditionType,
            @QueryParam("offerType") Set<OfferType> offerType
    ) {
        List<ProductDto> products = productService.getProducts(
                name, priceMin, priceMax, ratingMin, categoryType, conditionType, offerType
        );
        return Response.ok(products).build();
    }
}
