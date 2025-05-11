package entity.data;

import java.util.Optional;
import java.util.Set;

public enum PotionRecipeData {
	NIGHT_VISION(Set.of("NetherWart", "Carrot", "RedStone"), PotionData.NIGHT_VISION),
	FIRE_RESISTANCE(Set.of("NetherWart", "MagmaCream", "RedStone"), PotionData.FIRE_RESISTANCE),
	LEAPING(Set.of("NetherWart", "RabbitFoot", "GrowStone"), PotionData.LEAPING),
	SWIFTNESS(Set.of("NetherWart", "Sugar", "RedStone"), PotionData.SWIFTNESS),
	WATER_BREATHING(Set.of("NetherWart", "Pufferfish", "RedStone"), PotionData.WATER_BREATHING),
	HEALING(Set.of("NetherWart", "Watermelon", "GrowStone"), PotionData.HEALING),
	POISON(Set.of("NetherWart", "SpiderEye", "GrowStone"), PotionData.POISON),
	REGENERATION(Set.of("NetherWart", "GhastTear", "RedStone"), PotionData.REGENERATION),
	STRENGTH(Set.of("NetherWart", "BlazePowder", "GrowStone"), PotionData.STRENGTH);

	private final Set<String> ingredientsList;
	private final PotionData potion;

	PotionRecipeData(Set<String> ingredientsList, PotionData potion) {
		this.ingredientsList = ingredientsList;
		this.potion = potion;
	}

	public Set<String> getIngredients() {
		return ingredientsList;
	}

	public PotionData getPotion() {
		return potion;
	}

	public static Optional<PotionData> getPotionByIngredients(Set<String> inputIngredientsList) {
		for (PotionRecipeData recipe : values()) {
			if (recipe.ingredientsList.equals(inputIngredientsList)) {
				return Optional.of(recipe.potion);
			}
		}
		return Optional.empty();
	}
}