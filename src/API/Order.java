/**
 * Group 19
 * Aurélien Giuglaris Michael & Antoine Dorard
 * i6279204 & i6269522
 */
package API;

import Menu.Dish;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private static int numberOfOrders = 0;
    private List<Dish> plates;
    private boolean onsite;
    private OrderStatus status;
    private Date orderTime;
    // private boolean isPaid;

    public Order(List<Dish> plates, boolean onsite) {
        this.plates = plates;
        this.onsite = onsite;
        this.status = OrderStatus.TO_PREPARE;
        this.orderTime = new Date(System.currentTimeMillis());
        id = numberOfOrders;
        numberOfOrders++;
    }

    public float getPrice() {
        return API.getOrderPrice(this);
    }

    public boolean isOnsite() {
        return onsite;
    }

    public void setOnsite(boolean onsite) {
        this.onsite = onsite;
    }

    public int getId() {
        return id;
    }

    public static int getNumberOfOrders() {
        return numberOfOrders;
    }

    public List<Dish> getPlates() {
        return plates;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Date getOrderTime() {
        return orderTime;
    }
}
