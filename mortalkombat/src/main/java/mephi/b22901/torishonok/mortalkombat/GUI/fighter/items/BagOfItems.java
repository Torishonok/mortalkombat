package mephi.b22901.torishonok.mortalkombat.GUI.fighter.items;



import java.util.Random;
import mephi.b22901.torishonok.mortalkombat.GUI.fighter.Player;

public class BagOfItems {

    private SmallPotion smallPotion = new SmallPotion();
    private BigPotion bigPotion = new BigPotion();
    private CrossOfRevival crossOfRevival = new CrossOfRevival();
    private int random;

    public void dropItem() {
        random = new Random().nextInt(100) + 1;

        if (random <= 5) {
            crossOfRevival.setValue(crossOfRevival.getValue() + 1);
        } else if (random <= 20) {
            bigPotion.setValue(crossOfRevival.getValue() + 1);
        } else if (random <= 45) {
            smallPotion.setValue(smallPotion.getValue() + 1);
        }
    }
    public int getSmallPotionCount() {
        return smallPotion.getValue();
    }

    public int getBigPotionCount() {
        return bigPotion.getValue();
    }

    public int getCrossOfRevivalCount() {
        return crossOfRevival.getValue();
    }

    public void useItem(String itemName, Player player) {
        if (itemName.equals(smallPotion.getName()) && smallPotion.getValue() > 0) {
            smallPotion.addHp(player);
            smallPotion.setValue(smallPotion.getValue() - 1);
        } else if (itemName.equals(bigPotion.getName()) && bigPotion.getValue() > 0) {
            bigPotion.addHp(player);
            bigPotion.setValue(bigPotion.getValue() - 1);
        }
    }

    public void autoUsingCross(Player player) {
        crossOfRevival.addHp(player);
        crossOfRevival.setValue(crossOfRevival.getValue() - 1);
    }
}
