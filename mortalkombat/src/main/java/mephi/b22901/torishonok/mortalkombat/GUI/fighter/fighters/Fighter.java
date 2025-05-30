package mephi.b22901.torishonok.mortalkombat.GUI.fighter.fighters;

import mephi.b22901.torishonok.mortalkombat.GUI.fighter.Enemy;


public abstract class Fighter extends Enemy {

    Fighter() {
        super();
        this.damage = 50;
        this.maxHp = 85;
        this.type = "Боец";
        this.numberType = this.getBehaviorType(this.type);
        calculateInfo(this.level);
    }

    private void calculateInfo(int level) {
        switch (level) {
            case 0: {
                this.damage = 50;
                this.maxHp = 85;
                break;
            }
            case 1: {
                this.damage = 65;
                this.maxHp = 110;
                break;
            }
            case 2: {
                this.damage = 80;
                this.maxHp = 135;
                break;
            }
            case 3: {
                this.damage = 95;
                this.maxHp = 160;
                break;
            }
            case 4: {
                this.damage = 110;
                this.maxHp = 185;
                break;
            }
            case 5: {
                this.damage = 125;
                this.maxHp = 210;
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
