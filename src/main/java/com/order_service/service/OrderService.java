package com.order_service.service;

import com.order_service.dto.TransactionDataDTO;
import com.order_service.entity.Order;
import com.order_service.enums.OrderPreparationStatus;
import com.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public String prepareOrder(TransactionDataDTO transactionDataDTO) {
        Order order =new Order();
        order.setOrderId(transactionDataDTO.getOrderId());
        order.setItem(transactionDataDTO.getItem());
        order.setPreparationStatus(OrderPreparationStatus.PREPARING.name());
        orderRepository.save(order);
        if(isPrepareFailed()){
            throw new RuntimeException("Prepare failed");
        }
        return "Order prepared SuccessFully";
    }

    public String commitOrder(TransactionDataDTO transactionDataDTO) {
        return orderRepository.findByItem(transactionDataDTO.getItem())
                .filter(order -> OrderPreparationStatus.PREPARING.name().equalsIgnoreCase(order.getPreparationStatus()))
                .map(order -> {
                    order.setPreparationStatus(OrderPreparationStatus.COMMITTED.name());
                    orderRepository.save(order);
                    return "Order committed successfully";
                })
                .orElseThrow(() -> new RuntimeException("Order not found or not in preparing status"));
    }

    private boolean isPrepareFailed() {
        return true;
    }


    public String rollBackOrder(TransactionDataDTO transactionDataDTO) {
        return orderRepository.findByItem(transactionDataDTO.getItem())
                .map(order -> {
                    order.setPreparationStatus(OrderPreparationStatus.ROLLBACK.name());
                    orderRepository.save(order);
                    return "Order RollBack successfully";
                })
                .orElseThrow(() -> new RuntimeException("Order not found or not in preparing status"));
    }
}
