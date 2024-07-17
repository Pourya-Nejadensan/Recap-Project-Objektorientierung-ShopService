import java.util.List;
import java.util.Optional;

public interface OrderRepo {

    List<Order> getOrders();

    Optional<Order> getOrderById(String id);

    Order addOrder(Order newOrder);

    void removeOrder(String id);

    List<Order> getSpecificOrders(OrderStatus orderStatus);

    Order updateOrder(Order order);
}
