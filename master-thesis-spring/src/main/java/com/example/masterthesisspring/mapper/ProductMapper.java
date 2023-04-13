package com.example.masterthesisspring.mapper;

import com.example.masterthesisspring.dto.ProductAddDto;
import com.example.masterthesisspring.dto.ProductDto;
import com.example.masterthesisspring.dto.UserDto;
import com.example.masterthesisspring.exception.ApplicationException;
import com.example.masterthesisspring.exception.Error;
import com.example.masterthesisspring.model.Product;
import com.example.masterthesisspring.model.User;
import com.example.masterthesisspring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductMapper {

    private final UserRepository userRepository;

    public Product toEntity(ProductAddDto productAddDto) {
        User user = userRepository.findById(productAddDto.userId()).orElseThrow(() -> new ApplicationException(Error.USER_NOT_FOUND));

        Product product = new Product();
        product.setName(productAddDto.name());
        product.setPrice(productAddDto.price());
        product.setDescription(productAddDto.description());
        product.setRating(productAddDto.rating());
        product.setUser(user);
        product.setCategoryType(productAddDto.categoryType());
        product.setConditionType(productAddDto.conditionType());
        product.setOfferType(productAddDto.offerType());

        return product;
    }

    public ProductDto toDto(Product product) {
        UserDto userDto = new UserDto(
                product.getUser().getId(),
                product.getUser().getEmail(),
                product.getUser().getUsername(),
                product.getUser().getFirstName(),
                product.getUser().getSurname()
        );
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getRating(),
                userDto,
                product.getCategoryType(),
                product.getConditionType(),
                product.getOfferType()
        );
    }

    public List<ProductDto> toDto(List<Product> products) {
        List<ProductDto> productsDto = new ArrayList<>();
        products.forEach(product -> productsDto.add(this.toDto(product)));
        return productsDto;
    }
}
