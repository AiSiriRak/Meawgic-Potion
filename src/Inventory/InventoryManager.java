package Inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private static final Map<String, Integer> ingredientQuantities = new HashMap<>();
    private static final Map<String, Integer> potionQuantities = new HashMap<>();

    public static int getIngredient(String name) {
        return ingredientQuantities.getOrDefault(name, 0);
    }

    public static void addIngredient(String name, int amount) {
        ingredientQuantities.put(name, getIngredient(name) + amount);
    }

    public static void consumeIngredient(String name, int amount) {
        ingredientQuantities.put(name, Math.max(0, getIngredient(name) - amount));
    }

    public static int getPotion(String name) {
        return potionQuantities.getOrDefault(name, 0);
    }

    public static void addPotion(String name, int amount) {
        potionQuantities.put(name, getPotion(name) + amount);
    }
}
