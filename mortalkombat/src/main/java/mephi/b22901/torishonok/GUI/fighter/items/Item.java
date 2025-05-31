package mephi.b22901.torishonok.GUI.fighter.items;

import mephi.b22901.torishonok.GUI.fighter.Player;



public abstract class Item {
    protected int value;
    protected String name;

    public Item() {
        this.value = 0;
        this.name = "";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    protected abstract void addHp(Player player);
}
