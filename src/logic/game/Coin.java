package logic.game;

import entity.base.Basis;
import entity.base.Ingredient;

public class Coin {
    private int coin;

    public Coin() {
        this.coin = 100;
    }

    public boolean decreaseCoin(int amount) {
        if (amount <= coin) {
            coin -= amount;
            return true;
        }
        return false;
    }

    public void increaseCoin(int amount) {
        coin += amount;
    }

    public boolean tryPurchase(Basis basis) {
        return decreaseCoin(basis.getBuyPrice());
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public boolean canAfford(int amount) {
        return amount <= coin;
    }

    public boolean canAfford(Basis basis) {
        return basis.getBuyPrice() <= coin;
    }

}
