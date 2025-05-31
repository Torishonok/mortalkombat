package mephi.b22901.torishonok.GUI.fighter.items;

import mephi.b22901.torishonok.GUI.fighter.Player;



public class BigPotion extends Item {

    public BigPotion() {
        super();
        this.name = "Большое зелье лечения";
        this.value = 0;
    }

    @Override
    protected void addHp(Player player) {
        player.setCurrentHp(player.getCurrentHp() + ((int) (0.5 * player.getMaxHp())));
    }
}
