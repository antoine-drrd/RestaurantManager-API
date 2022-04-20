/**
 * Group 19
 * Aurélien Giuglaris Michael & Antoine Dorard
 * i6279204 & i6269522
 */
package Online.External;

import API.*;
import Menu.*;
import Online.WebAPI;

import java.util.ArrayList;
import java.util.List;

public class ExternalWebsiteAPI extends WebAPI {

    /**
     * To call when the payment is done on the delivery platform
     * @param dishIdsList : list of all the dishes, differentiated by their id, given by the platform
     */
    public static void submitOrderByDishId(List<Integer> dishIdsList){
        ArrayList<Dish> dishes = new ArrayList<>();
        for (Integer id : dishIdsList){
            dishes.add(API.menu.getDishById(id));
        }

        API.submitOrder(new Order(dishes, false));
    }


}
