package entity.data;

import java.util.*;

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

	private final Set<String> ingredients;
	private final PotionData result;

	PotionRecipeData(Set<String> ingredients, PotionData result) {
		this.ingredients = ingredients;
		this.result = result;
	}

	public Set<String> getIngredients() {
		return ingredients;
	}

	public PotionData getResult() {
		return result;
	}

	public static Optional<PotionData> getPotionByIngredients(Set<String> inputIngredients) {
		for (PotionRecipeData recipe : values()) {
			if (recipe.ingredients.equals(inputIngredients)) {
				return Optional.of(recipe.result);
			}
		}
		return Optional.empty();
	}
}