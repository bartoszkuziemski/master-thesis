package com.example.masterthesisspring.repository;

import com.example.masterthesisspring.dto.ProductSearchDto;
import com.example.masterthesisspring.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProductSpecification implements Specification<Product> {

    private final transient ProductSearchDto productSearchDto;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = new ArrayList<>();

        if (productSearchDto.name() != null) {
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("name")),
                            "%" + productSearchDto.name().toLowerCase() + "%"
                    )
            );
        }

        if (productSearchDto.priceMin() != null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get("price"), productSearchDto.priceMin()
                    )
            );
        }

        if (productSearchDto.priceMax() != null) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(
                            root.get("price"), productSearchDto.priceMax()
                    )
            );
        }

        if (productSearchDto.ratingMin() != null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get("rating"), productSearchDto.ratingMin()
                    )
            );
        }

        if (!CollectionUtils.isEmpty(productSearchDto.categoryType())) {
            predicates.add(
                    root.get("categoryType").in(productSearchDto.categoryType())
            );
        }

        if(!CollectionUtils.isEmpty(productSearchDto.conditionType())) {
            predicates.add(
                    root.get("conditionType").in(productSearchDto.conditionType())
            );
        }

        if (!CollectionUtils.isEmpty(productSearchDto.offerType())) {
            predicates.add(
                    root.get("offerType").in(productSearchDto.offerType())
            );
        }

        query.distinct(true);
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
