package mephi.b22901.torishonok.GUI.fighter;

import mephi.b22901.torishonok.GUI.fighter.bosses.ShaoKahn;
import mephi.b22901.torishonok.GUI.fighter.fighters.SubZero;
import mephi.b22901.torishonok.GUI.fighter.soldiers.Kitana;
import mephi.b22901.torishonok.GUI.fighter.tanks.Baraka;
import mephi.b22901.torishonok.GUI.fighter.tanks.SonyaBlade;
import mephi.b22901.torishonok.GUI.fighter.wizards.LiuKang;



public class EnemyFactory {

    public Enemy createEnemy(int number) {
        switch(number) {
            case 1 -> {
                return new Baraka();
            }
            case 2 -> {
                return new SonyaBlade();
            }
            case 3 -> {
                return new SubZero();
            }
            case 4 -> {
                return new LiuKang();
            }
            case 5 -> {
                return new Kitana();
            }
            case 6 -> {
                return new ShaoKahn();
            }
            default -> throw new IllegalArgumentException("Неизвестный тип врага");
        }
    }
}
