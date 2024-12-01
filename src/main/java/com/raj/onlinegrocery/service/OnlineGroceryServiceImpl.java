package com.raj.onlinegrocery.service;

import com.raj.onlinegrocery.dto.CustomerDto;
import com.raj.onlinegrocery.dto.GroceryDto;
import com.raj.onlinegrocery.dto.OrdersDto;
import com.raj.onlinegrocery.entity.CustomerEntity;
import com.raj.onlinegrocery.entity.GroceryEntity;
import com.raj.onlinegrocery.entity.OrderEntity;
import com.raj.onlinegrocery.exception.NoDataFoundException;
import com.raj.onlinegrocery.exchange.CreateResponse;
import com.raj.onlinegrocery.exchange.DeleteOrderResponse;
import com.raj.onlinegrocery.exchange.GiveOrderResponse;
import com.raj.onlinegrocery.repository.CustomerEntityRepository;
import com.raj.onlinegrocery.repository.GroceryEntityRepository;
import com.raj.onlinegrocery.repository.OrderEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OnlineGroceryServiceImpl implements OnlineGroceryService {

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Autowired
    private GroceryEntityRepository groceryEntityRepository;

    @Autowired
    private OrderEntityRepository orderEntityRepository;

    @Override
    public CreateResponse createCustomerService(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        // get details from customerDto and save to customerEntity
        customerEntity.setName(customerDto.getName());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setAddress(customerDto.getAddress());
        customerEntity.setPhone(customerDto.getPhone());
        // save the customerEntity to DB
        CustomerEntity savedCustomer = customerEntityRepository.save(customerEntity);
        // Create customerResponse with customerId set from savedCustomer
        CreateResponse customerCreateResponse = new CreateResponse(savedCustomer.getCustomerId());
        return customerCreateResponse;
    }

    @Override
    public CreateResponse updateCustomerService(Long customerId, CustomerDto customerDto) throws NoDataFoundException {
        CreateResponse customerCreateResponse = new CreateResponse();
        if (null != customerId) {
            // find the customer by customerId
            Optional<CustomerEntity> returnedCustomerOptional = customerEntityRepository.findById(customerId);
            if (returnedCustomerOptional.isPresent()) {
                CustomerEntity returnedCustomer = returnedCustomerOptional.get();
                returnedCustomer.setPhone(customerDto.getPhone());
                returnedCustomer.setEmail(customerDto.getEmail());
                returnedCustomer.setName(customerDto.getName());
                returnedCustomer.setAddress(customerDto.getAddress());
                CustomerEntity updatedCustomer = customerEntityRepository.save(returnedCustomer);
                customerCreateResponse.setId(updatedCustomer.getCustomerId());
                return customerCreateResponse;
            } else throw new NoDataFoundException("Invalid customer id");


        }
        return customerCreateResponse;
    }

    @Override
    public String removeCustomerService(Long customerId) throws NoDataFoundException {
        if (null != customerId) {
            // find the customer by customerId
            Optional<CustomerEntity> returnedCustomerOptional = customerEntityRepository.findById(customerId);
            if (returnedCustomerOptional.isPresent()) {
                CustomerEntity returnedCustomer = returnedCustomerOptional.get();
                customerEntityRepository.delete(returnedCustomer);
                return "deleted successfully";
            } else throw new NoDataFoundException("Invalid customer Id");
        }
        return "";
    }

    //=======================Grocery=======================================

    @Override
    public CreateResponse addGroceryService(GroceryDto groceryDto) {
        GroceryEntity groceryEntity = new GroceryEntity();
        // get details from customerDto and save to customerEntity
        groceryEntity.setName(groceryDto.getName());
        groceryEntity.setCategory(groceryDto.getCategory());
        groceryEntity.setQuantity(groceryDto.getQuantity());
        groceryEntity.setPrice(groceryDto.getPrice());

        // save the customerEntity to DB
        GroceryEntity savedGrocery = groceryEntityRepository.save(groceryEntity);
        // Create customerResponse with customerId set from savedCustomer
        CreateResponse groceryCreateResponse = new CreateResponse(savedGrocery.getGoroceryId());
        return groceryCreateResponse;
    }

    @Override
    public CreateResponse updateGroceryService(Long groceryId, GroceryDto groceryDto) throws NoDataFoundException {
        CreateResponse groceryCreateResponse = new CreateResponse();
        if (null != groceryId) {
            Optional<GroceryEntity> returnedGroceryOptional = groceryEntityRepository.findById(groceryId);
            if (returnedGroceryOptional.isPresent()) {
                GroceryEntity returnedGrocery = returnedGroceryOptional.get();
                returnedGrocery.setName(groceryDto.getName());
                returnedGrocery.setCategory(groceryDto.getCategory());
                returnedGrocery.setQuantity(groceryDto.getQuantity());
                returnedGrocery.setPrice(groceryDto.getPrice());
                GroceryEntity updatedGrocery = groceryEntityRepository.save(returnedGrocery);
                groceryCreateResponse.setId(updatedGrocery.getGoroceryId());
                return groceryCreateResponse;
            } else throw new NoDataFoundException("Invalid grocery id");


        }
        return groceryCreateResponse;
    }

    @Override
    public String removeGroceryService(Long groceryId) throws NoDataFoundException {
        if (null != groceryId) {
            Optional<GroceryEntity> returnedGroceryOptional = groceryEntityRepository.findById(groceryId);
            if (returnedGroceryOptional.isPresent()) {
                GroceryEntity returnedGrocery = returnedGroceryOptional.get();
                groceryEntityRepository.delete(returnedGrocery);
                return "deleted successfully";
            } else throw new NoDataFoundException("Invalid grodery Id");
        }
        return " ";
    }

    //====================================Orders==============================

    @Override
    public GiveOrderResponse giveOrderService(OrdersDto ordersDto, Long customerId) throws NoDataFoundException {
        OrderEntity orderEntity = new OrderEntity();
        GiveOrderResponse giveOrderResponse = new GiveOrderResponse();
        Double totalPricePerOrder = 0d;
        if (customerId != null) {
            // Find the customer from customerId
            Optional<CustomerEntity> customerOptional = customerEntityRepository.findById(customerId);
            //Optional<GroceryEntity> groceryOptional = groceryEntityRepository.findById(groceryId);
            if (customerOptional.isPresent()) {
                //get the customer from optional for validating customerId passed
                // and set the customer in orderEntity
                CustomerEntity returnedCustomer = customerOptional.get();
                orderEntity.setCustomer(returnedCustomer);

            } else {
                throw new NoDataFoundException("Invalid customer id");
            }

            // For grocery
            for (Long groceryId : ordersDto.getGroceryIds()) {
                Optional<GroceryEntity> groceryOptional = groceryEntityRepository.findById(groceryId);
                if (groceryOptional.isPresent()) {
                    //get the grocery from optional for validating groceryId passed
                    // and set the grocery in orderEntity
                    GroceryEntity returnedGrocery = groceryOptional.get();

                    // Calculate the price per grocery item and add calculate the total price for order
                    Double itemPrice = returnedGrocery.getPrice();
                    int itemQuantity = returnedGrocery.getQuantity();
                    Double totalPricePerItem = itemPrice * itemQuantity;
                    totalPricePerOrder = totalPricePerOrder + totalPricePerItem;

                    // Add each grocery to the list for saving to DB
                    orderEntity.getGroceries().add(returnedGrocery);


                } else {
                    throw new NoDataFoundException("Invalid grocery id");
                }

            }

            orderEntity.setPrice(totalPricePerOrder);
            orderEntity.setOrderDate(LocalDate.now());
            OrderEntity savedOrder = orderEntityRepository.save(orderEntity);

            giveOrderResponse.setOrderId(savedOrder.getOrderId());
            giveOrderResponse.setOrderDate(savedOrder.getOrderDate());
            giveOrderResponse.setGroceries(savedOrder.getGroceries());
            giveOrderResponse.setPrice(savedOrder.getPrice());
        }


        return giveOrderResponse;
    }

    @Override
    public DeleteOrderResponse removeOrderService(Long customerId, Long orderId) throws NoDataFoundException {
        DeleteOrderResponse deleteOrderResponse = new DeleteOrderResponse();
        if (null != customerId && null != orderId) {
            Optional<CustomerEntity> retrievedCustomerOptional = customerEntityRepository.findById(customerId);
            if (retrievedCustomerOptional.isPresent()) {
                CustomerEntity retrievedCustomer = retrievedCustomerOptional.get();
                //List of all orderId list for a customerId
                List<Long> orderIdsList = orderEntityRepository.findAllOrdersByCustomerId(retrievedCustomer.getCustomerId());
                System.out.println("orderIdsList :" + orderIdsList);

                if (orderIdsList.contains(orderId)) {
                    OrderEntity orderToDelete = orderEntityRepository.findById(orderId).get();
                    orderEntityRepository.delete(orderToDelete);

                    // After deletion of order
                    List<Long> orderIdsListAfterDelete = orderEntityRepository.findAllOrdersByCustomerId(retrievedCustomer.getCustomerId());
                    System.out.println("orderIdsListAfterDelete " + orderIdsListAfterDelete);
                    // Set the deleteOrderResponse and return
                    deleteOrderResponse.setRemainingGroceryIds(orderIdsListAfterDelete);
                    deleteOrderResponse.setCustomerId(customerId);
                    return deleteOrderResponse;


                } else {
                    throw new NoDataFoundException("Invalid order Id or customer Id");
                }

            }
        }
        return deleteOrderResponse;
    }


}
