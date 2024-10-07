package com.order_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "ORDER_DETAILS")
@Data
@Accessors(chain = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="ORDER_ID")
    private Integer orderId;

    @Column(name="ITEM")
    private String item;

    @Column(name="PREPARATION_STATUS")
    private String preparationStatus;
}
