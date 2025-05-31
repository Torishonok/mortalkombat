package mephi.b22901.torishonok.GUI.fighter.wizards;

import mephi.b22901.torishonok.GUI.fighter.Enemy;
public abstract class Wizard extends Enemy {

    Wizard() {
        super();
        this.damage = 35;
        this.currentHp = 75;
        this.maxHp = 75;
        this.type = "Маг";
        this.numberType = this.getBehaviorType(this.type);
        calculateInfo(this.level);
    }

    private void calculateInfo(int level) {
        switch (level) {
            case 0 : {this.damage = 35; this.maxHp = 75; break;}
            case 1 : {this.damage = 50; this.maxHp = 100; break;}
            case 2 : {this.damage = 65; this.maxHp = 125; break;}
            case 3 : {this.damage = 80; this.maxHp = 150; break;}
            case 4 : {this.damage = 95; this.maxHp = 175; break;}
            case 5 : {this.damage = 110; this.maxHp = 200; break;}
            default: throw new IllegalArgumentException("Некорректный уровень: " + level);
        }
        this.currentHp = this.maxHp;
    }

    @Override
    public void setLevel(int level) {
        super.setLevel(level);
        calculateInfo(level);
    }


}
