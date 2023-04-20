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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private Long threadsCommonId = 1000L;

    public ProductDto addProduct(ProductAddDto productAddDto) {
        Product product = productMapper.toEntity(productAddDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    public List<ProductDto> getAllProducts() {
        return productMapper.toDto(productRepository.findAll());
    }

    public String deleteProduct() {
        Long id = this.getNextId();
        productRepository.deleteById(id);
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
        Pageable pageable = PageRequest.of(0, 50);
        List<Product> products = productRepository.findAll(new ProductSpecification(productSearchDto), pageable).toList();
        return productMapper.toDto(products);
    }

    public ProductDto getProduct(Long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ApplicationException(Error.PRODUCT_NOT_FOUND));
        return productMapper.toDto(product);
    }

    private synchronized Long getNextId() {
        threadsCommonId++;
        return threadsCommonId;
    }
}
