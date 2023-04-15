package com.example.model;

import com.example.model.enums.CategoryType;
import com.example.model.enums.ConditionType;
import com.example.model.enums.OfferType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_type")
    private CategoryType categoryType;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_type")
    private ConditionType conditionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "offer_type")
    private OfferType offerType;
}
