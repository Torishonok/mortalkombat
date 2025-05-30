package mephi.b22901.torishonok.mortalkombat.GUI.fighter;

import java.util.Random;

public abstract class Enemy extends Entity {

    protected String photoPath;
    protected int numberType;
    protected String type;

    public Enemy() {
        super();
        this.currentHp = 200;
        this.level = 0;
        this.photoPath = "";
    }

    public int getNumberType() {
        return numberType;
    }

    public void setNumberType(int numberType) {
        this.numberType = numberType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    protected int getBehaviorType(String type) {
        int[] temp;
        int rand;
        switch (type) {
            case "Маг" -> {
                temp = new int[]{1, 3};
                return temp[new Random().nextInt(1, 100) < 50 ? 0 : 1];
            }
            case "Солдат" -> {
                temp = new int[]{1, 2};
                return temp[new Random().nextInt(1, 100) < 50 ? 0 : 1];
            }
            case "Танк" -> {
                rand = new Random().nextInt(0, 100);
                if (rand <= 30) {
                    return 1;
                } else if (rand <= 90) {
                    return 2;
                } else if (rand <= 100) {
                    return 3;
                } else {
                    System.out.println("Ошибка определения типа врага!");
                    return 0;
                }
            }
            case "Боец" -> {
                rand = new Random().nextInt(0, 100);
                if (rand <= 25) {
                    return 1;
                } else if (rand <= 35) {
                    return 2;
                } else if (rand <= 100) {
                    return 3;
                } else {
                    System.out.println("Ошибка определения типа врага!");
                    return 0;
                }
            }
            case "Босс" -> {
                rand = new Random().nextInt(0, 100);
                if (rand <= 33) {
                    return 1;
                } else if (rand <= 66) {
                    return 2;
                } else if (rand <= 100) {
                    return 3;
                } else {
                    System.out.println("Ошибка определения типа врага!");
                    return 0;
                }
            }
            default -> {
                System.out.println("Ошибка определения типа врага!");
                return 0;
            }
        }
    }

}







