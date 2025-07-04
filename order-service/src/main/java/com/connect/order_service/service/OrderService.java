package com.connect.order_service.service;

import com.connect.order_service.config.ClientConfig;
import com.connect.order_service.dto.OrderRequest;
import com.connect.order_service.dto.OrderResponse;
import com.connect.order_service.entity.Order;
import com.connect.order_service.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
public class OrderService
{

    private final OrderRepository orderRepository;
    private final ClientConfig clientConfig;


    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackPlaceOrder")
    @TimeLimiter(name = "inventory", fallbackMethod = "fallbackPlaceOrder")
    @Retry(name = "inventory", fallbackMethod = "fallbackPlaceOrder")
    public CompletableFuture<String> placeOrder(OrderRequest orderRequest) {

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .skuCode(orderRequest.getSkuCode())
                .price(orderRequest.getPrice())
                .quantity(orderRequest.getQuantity())
                .build();

        return CompletableFuture.supplyAsync(() ->
        {
            if(clientConfig.checkInventory(orderRequest.getSkuCode(), orderRequest.getQuantity())){
                orderRepository.save(order);
                return "Order Placed";
            }
            else {
                return "Product unavailable";
            }
        });
    }


    private CompletableFuture<String> fallbackPlaceOrder(OrderRequest orderRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please try again later!");
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