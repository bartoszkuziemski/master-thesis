package com.example.service;

import com.example.dto.ProductAddDto;
import com.example.dto.ProductDto;
import com.example.dto.ProductSearchDto;
import com.example.exception.ApplicationException;
import com.example.exception.Error;
import com.example.mapper.ProductMapper;
import com.example.model.Product;
import com.example.model.enums.CategoryType;
import com.example.model.enums.ConditionType;
import com.example.model.enums.OfferType;
import com.example.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@ApplicationScoped
@RequiredArgsConstructor
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductDto addProduct(ProductAddDto productAddDto) {
        Product product = productMapper.toEntity(productAddDto);
        productRepository.persist(product);
        return productMapper.toDto(product);
    }

    public List<ProductDto> getAllProducts() {
        return productMapper.toDto(productRepository.listAll());
    }

    @Transactional
    public String deleteProduct(Long id) {
        boolean isDeleted = productRepository.deleteById(id);
        if (!isDeleted) {
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
        List<Product> products = productRepository.findByProductSearchDto(productSearchDto, 0, 50);
        return productMapper.toDto(products);
    }

    public ProductDto getProduct(Long id) {
        Product product = productRepository
                .findByIdOptional(id)
                .orElseThrow(() -> new ApplicationException(Error.PRODUCT_NOT_FOUND));
        return productMapper.toDto(product);
    }
}
