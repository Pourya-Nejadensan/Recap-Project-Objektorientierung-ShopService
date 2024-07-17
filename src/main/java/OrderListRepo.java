import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderListRepo implements OrderRepo{
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public Optional<Order> getOrderById(String id) {
        return orders.stream()
                .filter(order -> order.id().equals(id))
                .findFirst();
    }

    public Order addOrder(Order newOrder) {
        orders.add(newOrder);
        return newOrder;
    }

    public void removeOrder(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                orders.remove(order);
                return;
            }
        }
    }

    public List<Order> getSpecificOrders(OrderStatus orderStatus) {
        return orders.stream().filter(x -> x.orderStatus().equals(orderStatus)).toList();
    }

    @Override
    public Order updateOrder(Order updatedOrder) {
        getOrderById(updatedOrder.id()).ifPresent(x -> {
            orders.remove(updatedOrder);
            orders.add(updatedOrder);
        });
        return updatedOrder;
    }
}
