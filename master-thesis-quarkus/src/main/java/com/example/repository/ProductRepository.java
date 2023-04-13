package com.example.repository;

import com.example.dto.ProductSearchDto;
import com.example.model.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.apache.commons.collections4.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    @Inject
    EntityManager entityManager;

    public List<Product> findByProductSearchDto(ProductSearchDto productSearchDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

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

        if (!CollectionUtils.isEmpty(productSearchDto.conditionType())) {
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
        query.select(root).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
