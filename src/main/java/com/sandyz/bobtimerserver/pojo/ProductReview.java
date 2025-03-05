package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "product_review")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @Column(nullable = false)
    private Integer goodId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Integer rating;

    @Column(length = 3000)
    private String reviewText;

    @Column(nullable = false)
    private Timestamp reviewTime;
}