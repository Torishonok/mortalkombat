package mephi.b22901.torishonok.mortalkombat.GUI.fighter.soldiers;

import mephi.b22901.torishonok.mortalkombat.GUI.fighter.Enemy;


public abstract class Soldier extends Enemy {

    Soldier() {
        super();
        this.damage = 35;
        this.maxHp = 100;
        this.type = "Солдат";
        this.numberType = this.getBehaviorType(this.type);
        calculateInfo(this.level);
    }

    private void calculateInfo(int level) {
        switch (level) {
            case 0: {
                this.damage = 35;
                this.maxHp = 100;
                break;
            }
            case 1: {
                this.damage = 50;
                this.maxHp = 125;
                break;
            }
            case 2: {
                this.damage = 65;
                this.maxHp = 150;
                break;
            }
            case 3: {
                this.damage = 80;
                this.maxHp = 175;
                break;
            }
            case 4: {
                this.damage = 95;
                this.maxHp = 200;
                break;
            }
            case 5: {
                this.damage = 110;
                this.maxHp = 225;
                break;
            }
            default:
                throw new IllegalArgumentException("Некорректный уровень: " + level);
        }
        this.currentHp = this.maxHp;
    }

    @Override
    public void setLevel(int level) {
        super.setLevel(level);
        calculateInfo(level);
    }
}
