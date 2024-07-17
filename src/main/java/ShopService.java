import exception.OrderNotFoundException;
import exception.ProductNotFoundException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds){
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                throw new ProductNotFoundException("Product with ID: " + productId + " could not be found!");
            }
            products.add(productToOrder.get());
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, Instant.now(), OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getSpecificOrders(OrderStatus orderStatus){
        return orderRepo.getSpecificOrders(orderStatus);
    }

    public Order updateOrder(String orderId, OrderStatus newStatus) {
        Optional<Order> existingOrder = orderRepo.getOrderById(orderId);
        if (existingOrder.isEmpty()) {
            throw new OrderNotFoundException("Order with ID: " + orderId + " does not exist!");
        }

        Order updatedOrder = existingOrder.get().withOrderStatus(newStatus);
        return orderRepo.updateOrder(updatedOrder);
    }
}
