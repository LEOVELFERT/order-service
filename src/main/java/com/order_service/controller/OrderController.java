package com.order_service.controller;

import com.order_service.dto.TransactionDataDTO;
import com.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/prepare")
    public ResponseEntity<String> prepareOrder(@RequestBody TransactionDataDTO transactionDataDTO) {
        try {
            return new ResponseEntity<>(orderService.prepareOrder(transactionDataDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error happened while preparing the order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/commit")
    public ResponseEntity<String> commitOrder(@RequestBody TransactionDataDTO transactionDataDTO) {
        try {
            return new ResponseEntity<>(orderService.commitOrder(transactionDataDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error happened while commiting the order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/rollback")
    public ResponseEntity<String> rollBackOrder(@RequestBody TransactionDataDTO transactionDataDTO) {
        try {
            return new ResponseEntity<>(orderService.rollBackOrder(transactionDataDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error happened while rollbacking the order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
