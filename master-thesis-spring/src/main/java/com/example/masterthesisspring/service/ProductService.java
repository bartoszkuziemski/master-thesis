package com.example.masterthesisspring.service;

import com.example.masterthesisspring.dto.ProductAddDto;
import com.example.masterthesisspring.dto.ProductDto;
import com.example.masterthesisspring.dto.ProductSearchDto;
import com.example.masterthesisspring.exception.ApplicationException;
import com.example.masterthesisspring.exception.Error;
import com.example.masterthesisspring.mapper.ProductMapper;
import com.example.masterthesisspring.model.Product;
import com.example.masterthesisspring.model.enums.CategoryType;
import com.example.masterthesisspring.model.enums.ConditionType;
import com.example.masterthesisspring.model.enums.OfferType;
import com.example.masterthesisspring.repository.ProductRepository;
import com.example.masterthesisspring.repository.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto addProduct(ProductAddDto productAddDto) {
        Product product = productMapper.toEntity(productAddDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    public List<ProductDto> getAllProducts() {
        return productMapper.toDto(productRepository.findAll());
    }

    public String deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            LOGGER.warn("Product with id {} does not exist", id);
            throw new ApplicationException(Error.PRODUCT_NOT_FOUND);
        }
        return "Product deleted";
    }

    public Long getProductsNumber() {
        return productRepository.count();
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    public List<ProductDto> getProducts(
            String name,
            Double priceMin,
            Double priceMax,
            Double ratingMin,
            Set<CategoryType> categoryType,
            Set<ConditionType> conditionType,
            Set<OfferType> offerType) {
        ProductSearchDto productSearchDto = new ProductSearchDto(
                name,
                priceMin,
                priceMax,
                ratingMin,
                categoryType,
                conditionType,
                offerType
        );
        List<Product> products = productRepository.findAll(new ProductSpecification(productSearchDto));
        return productMapper.toDto(products);
    }

    public ProductDto getProduct(Long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ApplicationException(Error.PRODUCT_NOT_FOUND));
        return productMapper.toDto(product);
    }
}
