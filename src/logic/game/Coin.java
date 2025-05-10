package logic.game;

public class Coin {
    private int coin;
    private boolean isEnoughCoin;
    private boolean isWarning;

    public Coin() {
        this.setCoin(100);
        this.isWarning = false;
        this.isEnoughCoin = true;
    }
    
    public boolean canAfford(int price) {
        return coin >= price;
    }

    public void increaseCoin(int amount) {
        coin += amount;
        GameController.updateCoinDisplay();
    }

    public boolean tryPurchase(int price) {
        if (canAfford(price)) {
            coin -= price;
            GameController.updateCoinDisplay();
            return true;
        }
        else {
			System.out.println("Not Enough Money");
			this.isEnoughCoin = false;

			this.isWarning = true;
			Thread warningCountdown = new Thread(() -> {
				while (this.isWarning) {
					try {
						GameController.warningCoinPane.setVisible(true);
						Thread.sleep(1000);
						GameController.warningCoinPane.setVisible(false);
						this.isWarning = false;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			warningCountdown.start();
        }
        return false;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
    
    @Override
    public String toString() {
    	return String.valueOf(coin);
    }

	public boolean isEnoughCoin() {
		return isEnoughCoin;
	}

	public void setEnoughCoin(boolean isEnoughCoin) {
		this.isEnoughCoin = isEnoughCoin;
	}

	public boolean isWarning() {
		return isWarning;
	}

	public void setWarning(boolean isWarning) {
		this.isWarning = isWarning;
	}

}
