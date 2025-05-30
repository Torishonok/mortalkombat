package mephi.b22901.torishonok.mortalkombat.GUI.fighter.items;

import mephi.b22901.torishonok.mortalkombat.GUI.fighter.Player;



public class SmallPotion extends Item{

    public SmallPotion() {
        this.name = "Малое зелье лечения";
    }

    @Override
    protected void addHp(Player player) {
        player.setCurrentHp(player.getCurrentHp() + ((int) (0.25 * player.getMaxHp())));
    }
}
