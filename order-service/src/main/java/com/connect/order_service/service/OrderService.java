package com.connect.order_service.service;

import com.connect.order_service.dto.OrderRequest;
import com.connect.order_service.dto.OrderResponse;
import com.connect.order_service.entity.Order;
import com.connect.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderService
{

    private final OrderRepository orderRepository;


    public void placeOrder(OrderRequest orderRequest) {

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .skuCode(orderRequest.getSkuCode())
                .price(orderRequest.getPrice())
                .quantity(orderRequest.getQuantity())
                .build();

        orderRepository.save(order);
    }

    public List<OrderResponse> getAllOrder() {

        List<Order> order = orderRepository.findAll();

        return order.stream()
                .map(this::getOrderResponse)
                .toList();

    }

    public OrderResponse getOrderResponse(Order order){

        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .skuCode(order.getSkuCode())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .build();
    }
}