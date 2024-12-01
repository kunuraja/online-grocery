package com.raj.onlinegrocery.service;

import com.raj.onlinegrocery.dto.CustomerDto;
import com.raj.onlinegrocery.dto.GroceryDto;
import com.raj.onlinegrocery.dto.OrdersDto;
import com.raj.onlinegrocery.exception.NoDataFoundException;
import com.raj.onlinegrocery.exchange.CreateResponse;
import com.raj.onlinegrocery.exchange.DeleteOrderResponse;
import com.raj.onlinegrocery.exchange.GiveOrderResponse;

public interface OnlineGroceryService {
    CreateResponse createCustomerService(CustomerDto customerDto);

    CreateResponse updateCustomerService(Long customerId, CustomerDto customerDto) throws NoDataFoundException;

    String removeCustomerService(Long customerId) throws NoDataFoundException;

    CreateResponse addGroceryService(GroceryDto groceryDto);

    CreateResponse updateGroceryService(Long groceryId, GroceryDto groceryDto) throws NoDataFoundException;

    String removeGroceryService(Long groceryId) throws NoDataFoundException;

    GiveOrderResponse giveOrderService(OrdersDto ordersDto, Long customerId) throws NoDataFoundException;

    DeleteOrderResponse removeOrderService(Long customerId, Long orderId) throws NoDataFoundException;



}

