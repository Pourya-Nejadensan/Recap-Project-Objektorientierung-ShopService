import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapRepoTest {

    @Test
    void getOrders() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), Instant.now(), OrderStatus.PROCESSING);
        repo.addOrder(newOrder);

        //WHEN
        List<Order> actual = repo.getOrders();

        //THEN
        List<Order> expected = new ArrayList<>();
        Product product1 = new Product("1", "Apfel");
        expected.add(new Order("1", List.of(product1), Instant.now(), OrderStatus.PROCESSING));

        assertEquals(actual, expected);
    }

    @Test
    void getOrderById() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), Instant.now(), OrderStatus.PROCESSING);
        repo.addOrder(newOrder);

        //WHEN
        Optional<Order> actual = repo.getOrderById("1");

        //THEN
        Product product1 = new Product("1", "Apfel");
        Optional<Order> expected = Optional.of(new Order("1", List.of(product1), Instant.now(), OrderStatus.PROCESSING));

        assertEquals(actual, expected);
    }

    @Test
    void addOrder() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();
        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product),Instant.now(), OrderStatus.PROCESSING);

        //WHEN
        Optional<Order> actual = Optional.of(repo.addOrder(newOrder));

        //THEN
        Product product1 = new Product("1", "Apfel");
        Optional<Order> expected = Optional.of(new Order("1", List.of(product1), Instant.now(), OrderStatus.PROCESSING));
        assertEquals(actual, expected);
        assertEquals(repo.getOrderById("1"), expected);
    }

    @Test
    void removeOrder() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();

        //WHEN
        repo.removeOrder("1");

        //THEN
        assertFalse(repo.getOrderById("1").isPresent());
    }
}
