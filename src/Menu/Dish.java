/**
 * Group 19
 * Aurélien Giuglaris Michael & Antoine Dorard
 * i6279204 & i6269522
 */
package Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dish {
    private int id;
    private String name;
    private float price;
    private List<Ingredient> ingredients;
    private DishType dishType;

    public Dish(String name, float price, DishType dishType, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.price = price;
        this.dishType = dishType;
        this.ingredients = ingredients;
        this.id = Objects.hash(name, price, ingredients);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public DishType getDishType() {
        return dishType;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public boolean addIngredient(Ingredient ingredient){
        for (Ingredient i : getIngredients()) {
            if(ingredient.equals(i))
                return false;
        }
        this.ingredients.add(ingredient);
        return true;
    }

    public int getId() {
        return id;
    }

    public boolean removeIngredient(Ingredient ingredient) {
        return this.ingredients.remove(ingredient);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Float.compare(dish.price, price) == 0 && Objects.equals(name, dish.name) && Objects.equals(ingredients, dish.ingredients);
    }
}
