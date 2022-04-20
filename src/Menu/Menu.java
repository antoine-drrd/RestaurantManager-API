/**
 * Group 19
 * Aurélien Giuglaris Michael & Antoine Dorard
 * i6279204 & i6269522
 */
package Menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    public List<Dish> dishes;

    public Menu(List<Dish> dishes) {
        this.dishes = dishes;
        dishes = new ArrayList<>();
    }

    public Menu() {
        dishes = new ArrayList<>();
    }

    public boolean addDish(Dish dish) {
        for (Dish d : this.dishes) {
            if(d.equals(dish)) return false;
        }

        dishes.add(dish);
        return true;
    }

    public boolean removeDish(Dish dish) {
        return dishes.remove(dish);
    }

    public Dish getDishById(int id){
        for(Dish d : dishes){
            if(d.getId() == id){
                return d;
            }
        }

        throw new IllegalArgumentException("No such dish id exists");
    }
}
