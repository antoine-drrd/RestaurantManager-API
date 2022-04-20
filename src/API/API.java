/**
 * Group 19
 * Aurélien Giuglaris Michael & Antoine Dorard
 * i6279204 & i6269522
 */
package API;

import Menu.*;
import Onsite.Table;
import Onsite.TableSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static Menu.Ingredient.*;

public class API {
    public static final RestaurantType RESTAURANT_TYPE = RestaurantType.A;
    public static TableSet tables = new TableSet(10);
    public static Menu menu = new Menu(new ArrayList<>(Arrays.asList(
            new Dish("Carbonara", 16.5f, DishType.MEAL, new ArrayList<>(Arrays.asList(PASTA, CREAM, EGG, PARMIGIANO))),
            new Dish("MOJITO", 7f, DishType.COCKTAIL, new ArrayList<>(Arrays.asList(MINT, LIME, RUM, SPARKLING_WATER)))

    )));

    private static Queue<Order> toBePrepared = new LinkedList<>();
    private static Queue<Order> toBeServed = new LinkedList<>();
    private static Queue<Order> toBeDelivered = new LinkedList<>();

    private static List<Order> toBePaid = new ArrayList<>();

    private static List<Order> orders = new ArrayList<>(); // Used as orders history

    public static void submitOrder(Order order){
        orders.add(order);
        toBePrepared.offer(order);
    }

    public static Queue<Order> getOrdersToPrepare(){
        return toBePrepared;
    }

    public static Queue<Order> getOrdersToServe(){
        return toBeServed;
    }

    public static List<Order> getOrdersToPay(){
        return toBePaid;
    }

    public static Queue<Order> getOrdersToDeliver(){
        return toBeDelivered;
    }

    public static List<Order> getAllOrders(){
        return orders;
    }

    /**
     *
     * @param order
     */
    public static void orderPrepared(Order order){
        if(order.isOnsite()){
            setOrderStatus(order, OrderStatus.TO_SERVE);
        }
        else{
            setOrderStatus(order, OrderStatus.TO_DELIVER);
        }
    }

    public static void orderServed(Order order){
        setOrderStatus(order, OrderStatus.TO_PAY);
    }

    public static void orderOnsiteCompleted(Order order){
        setOrderStatus(order, OrderStatus.COMPLETED);
    }

    public static void orderDelivered(Order order){
        setOrderStatus(order, OrderStatus.COMPLETED);
    }

    private static void setOrderStatus(Order order, OrderStatus status){
        order.setStatus(status);
        switch(status){
            case TO_SERVE -> {
                toBePrepared.remove(order);
                toBeServed.offer(order);
                order.setStatus(OrderStatus.TO_SERVE);
            }
            case TO_DELIVER -> {
                toBePrepared.remove(order);
                toBeDelivered.offer(order);
                order.setStatus(OrderStatus.TO_DELIVER);
            }
            case TO_PAY -> {
                toBeServed.remove(order);
                toBePaid.add(order);
                order.setStatus(OrderStatus.TO_PAY);
            }
            case COMPLETED -> {
                toBeServed.remove(order);
                toBePaid.remove(order);
                order.setStatus(OrderStatus.COMPLETED);
            }
        }
    }


    public static float getOrderPrice(Order order) {
        float price = 0;

        switch(API.RESTAURANT_TYPE){
            case A -> {
                if(order.isOnsite()){
                    for (Dish d : order.getPlates()) {
                        if(d.getDishType().equals(DishType.MEAL)){
                            price += d.getPrice() * 1.1f;
                        }else{
                            price += d.getPrice();
                        }
                    }
                }
                else{
                    for (Dish d : order.getPlates()) {
                        price += d.getPrice();
                    }
                }
            }
            case B -> {
                for (Dish d : order.getPlates()) {
                    price += d.getPrice();
                }

                boolean inTimeRange = false;
                try {
                    inTimeRange = isOrderInTimeRange(order, new SimpleDateFormat("HH.mm").parse("10.00"), new SimpleDateFormat("HH.mm").parse("11.00"));
                }
                catch(ParseException e){
                    e.printStackTrace();
                }

                if(inTimeRange){
                    price *= 0.95;
                }

            }
            case C -> {
                for (Dish d : order.getPlates()) {
                    price += d.getPrice();
                }

                boolean inTimeRange = false;
                try {
                    inTimeRange = isOrderInTimeRange(order, new SimpleDateFormat("HH.mm").parse("16.00"), new SimpleDateFormat("HH.mm").parse("18.00"));
                }
                catch(ParseException e){
                    e.printStackTrace();
                }


                boolean cocktailPreviously = false;
                for (Dish d : order.getPlates()) {
                    if(inTimeRange) {
                        if (d.getDishType().equals(DishType.COCKTAIL)) {
                            if (!cocktailPreviously) {
                                price += d.getPrice();
                            }
                            cocktailPreviously = !cocktailPreviously;
                        } else {
                            if(order.isOnsite()){
                                if (d.getDishType().equals(DishType.MEAL)){
                                    price += d.getPrice() * 1.05f;
                                }else{
                                    price += d.getPrice();
                                }
                            }else{
                                price += d.getPrice();
                            }
                        }
                    }else{
                        if(order.isOnsite()){
                            if (d.getDishType().equals(DishType.MEAL)){
                                price += d.getPrice() * 1.05f;
                            }else{
                                price += d.getPrice();
                            }
                        }else{
                            price += d.getPrice();
                        }
                    }
                }


            }
        }
        return price;
    }

    public static float getCartPrice(List<Dish> dishes) {
        return getOrderPrice(new Order(dishes, false));
    }

    private static boolean isOrderInTimeRange(Order order, Date start, Date end) {
        return order.getOrderTime().after(start)&& order.getOrderTime().before(end);
    }

    public static Menu getMenu() {
        return menu;
    }
}
