/**
 * Group 19
 * Aurélien Giuglaris Michael & Antoine Dorard
 * i6279204 & i6269522
 */
package Online.App;

import API.API;
import API.Order;
import Menu.Dish;
import Online.WebAPI;

public class WebAppAPI extends WebAPI {

    public static void checkout(WebClient client){
        // Creation of the order (saves the current time to compute the price)
        Order orderToSubmit = new Order(client.getCart(), false);

        // ---------------------------------
        // Ask the client to do the payment
        // ---------------------------------

        // Once payment done and verified:
        API.submitOrder(orderToSubmit);
        client.resetCart();
    }

    public static void addToCart(WebClient client, Dish dish){
        client.addToCart(dish);
    }

    public static void removeFromCart(WebClient client, Dish dish){
        client.removeFromCart(dish);
    }

}
