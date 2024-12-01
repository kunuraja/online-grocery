package com.raj.onlinegrocery.controller;

import com.raj.onlinegrocery.dto.CustomerDto;
import com.raj.onlinegrocery.dto.GroceryDto;
import com.raj.onlinegrocery.dto.OrdersDto;
import com.raj.onlinegrocery.exception.NoDataFoundException;
import com.raj.onlinegrocery.exchange.CreateResponse;
import com.raj.onlinegrocery.exchange.DeleteOrderResponse;
import com.raj.onlinegrocery.exchange.GiveOrderResponse;
import com.raj.onlinegrocery.service.OnlineGroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OnlineGroceryController {

    @Autowired
    private OnlineGroceryService onlineGroceryService;

    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {
        CreateResponse customerCreateResponse = onlineGroceryService.createCustomerService(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerCreateResponse);
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDto customerDto) {
        CreateResponse customerCreateResponse = null;
        try {
            customerCreateResponse = onlineGroceryService.updateCustomerService(customerId, customerDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerCreateResponse);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> removeCustomer(@PathVariable Long customerId) {
        String deletedSuccessfully = null;
        try {
            deletedSuccessfully = onlineGroceryService.removeCustomerService(customerId);
            return ResponseEntity.status(HttpStatus.OK).body(deletedSuccessfully);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    //================Grocery===============

    @PostMapping("/groceries")
    public ResponseEntity<?> addGrocery(@RequestBody GroceryDto groceryDto) {
        CreateResponse createResponse = onlineGroceryService.addGroceryService(groceryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createResponse);
    }

    @PutMapping("/groceries/{groceryId}")
    public ResponseEntity<?> updateGrocery(@PathVariable Long groceryId, @RequestBody GroceryDto groceryDto) {
        CreateResponse createResponse = null;
        try {
            createResponse = onlineGroceryService.updateGroceryService(groceryId, groceryDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createResponse);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping("/customers/{groceryId}")
    public ResponseEntity<?> removeGrocery(@PathVariable Long groceryId) {
        String deletedSuccessfully = null;
        try {
            deletedSuccessfully = onlineGroceryService.removeGroceryService(groceryId);
            return ResponseEntity.status(HttpStatus.OK).body(deletedSuccessfully);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    //==========================order====================

    @PostMapping("/customers/{customerId}/orders")
    public ResponseEntity<?> giveOrder(@RequestBody OrdersDto ordersDto, @PathVariable Long customerId) {
        GiveOrderResponse giveOrderResponse = null;
        try {
            giveOrderResponse = onlineGroceryService.giveOrderService(ordersDto, customerId);
            return ResponseEntity.status(HttpStatus.CREATED).body(giveOrderResponse);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping("/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<?> removeOrder(@PathVariable Long customerId, @PathVariable Long orderId) {
        DeleteOrderResponse deleteOrderResponse = null;
        try {
            deleteOrderResponse = onlineGroceryService.removeOrderService(customerId, orderId);
            return ResponseEntity.status(HttpStatus.OK).body(deleteOrderResponse);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


}
