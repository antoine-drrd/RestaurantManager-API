/**
 * Group 19
 * Aurélien Giuglaris Michael & Antoine Dorard
 * i6279204 & i6269522
 */
package Online;

import API.API;
import API.Order;
import Menu.Dish;
import Menu.Menu;

import java.util.List;

public abstract class WebAPI {

    public static float getDishPrice(int dishId){
        return getDishPrice(API.menu.getDishById(dishId));
    }
    public static float getCartPrice(List<Dish> dishList) {
        return API.getCartPrice(dishList);
    }

    public static float getDishPrice(Dish dish){
        return dish.getPrice();
    }

    public static Menu getMenu(){
        return API.getMenu();
    }

    /**
     * To call when the payment is done on the delivery platform
     * @param dishList: list of all the dishes as Dish objects, given by the platform
     */
    public static void submitOrder(List<Dish> dishList){
        API.submitOrder(new Order(dishList, false));
    }
}
