/**
 * Group 19
 * Aurélien Giuglaris Michael & Antoine Dorard
 * i6279204 & i6269522
 */
package Online.App;

import API.API;
import Menu.Dish;
import java.util.ArrayList;
import java.util.List;

public class WebClient {
    private List<Dish> cart;
    private String addressToDeliver;

    public WebClient(){
        this.cart = new ArrayList<>();
    }

    public void addToCart(Dish dish){
        this.cart.add(dish);
    }

    public void removeFromCart(Dish dish){
        this.cart.remove(dish);
    }

    public float getCartPrice() {
        return API.getCartPrice(cart);
    }
    
    public List<Dish> getCart(){
        return this.cart;
    }

    public void resetCart() {
        this.cart = new ArrayList<>();
    }


}
