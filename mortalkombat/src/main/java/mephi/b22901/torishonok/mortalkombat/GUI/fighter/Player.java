package mephi.b22901.torishonok.mortalkombat.GUI.fighter;

import mephi.b22901.torishonok.mortalkombat.GUI.fighter.Enemy;

public class Player extends Entity {

    private int points;
    private int valueOfExp;
    private int expToNextLevel;
    private int baseExp;
    private int currentLocation;

    public Player() {
        super();
        this.name = "Игрок";
        this.currentHp = 200;
        this.level = 0;
        this.damage = 100;
        this.maxHp = 200;
        this.points = 0;
        this.valueOfExp = 0;
        this.expToNextLevel = 30;
        this.baseExp = 0;
        this.currentLocation = 0;
    }

    public int getExpToNextLevel() {
        return expToNextLevel;
    }

    public int getPoints() {
        return points;
    }

    public int getValueOfExp() {
        return valueOfExp;
    }

    public void setExpToNextLevel(int expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setValueOfExp(int valueOfExp) {
        this.valueOfExp = valueOfExp;
    }

    public void calculatePlayerLevel(int numberDefeatedEnemies, boolean flagDebag) {
        switch (numberDefeatedEnemies) {
            case 2 -> { setLevel(1); calculateDamageHp(flagDebag); }
            case 4 -> { setLevel(2); calculateDamageHp(flagDebag); }
            case 7 -> { setLevel(3); calculateDamageHp(flagDebag); }
            case 9 -> { setLevel(4); calculateDamageHp(flagDebag); }
            case 12 -> { setLevel(5); calculateDamageHp(flagDebag); }
        }
    }

    public void calculatePoints(int cuttentHp, int maxHp) {

        int oneThirdHp = maxHp / 3;
        int twoThirdsHp = (maxHp * 2) / 3;

        if (cuttentHp <= oneThirdHp) {
            setPoints(getPoints() + 10);
        } else if (cuttentHp <= twoThirdsHp) {
            setPoints(getPoints() + 20);
        } else {
            setPoints(getPoints() + 30);
        }
    }

    private void calculateDamageHp(boolean fladDebag) {
        if (!fladDebag) {
            setDamage(getDamage() + 35);
            setMaxHp(getMaxHp() + 55);
        }
    }

    public void calculateExp(Enemy enemy, int numberLocation) {
        if (getCurrentLocation() != numberLocation) {
            baseExp = 0;
            setCurrentLocation(numberLocation);
        }

        switch (numberLocation) {
            case 1 -> {
                setExpToNextLevel(30);
                baseExp += enemy.getType().equals("Босс") ? (getExpToNextLevel() - baseExp) : 10;
            }
            case 2 -> {
                setExpToNextLevel(70);
                baseExp += enemy.getType().equals("Босс") ? (getExpToNextLevel() - baseExp) : 15;
            }
            case 3 -> {
                setExpToNextLevel(100);
                baseExp += enemy.getType().equals("Босс") ? (getExpToNextLevel() - baseExp) : 20;
            }
            case 4 -> {
                setExpToNextLevel(150);
                baseExp += enemy.getType().equals("Босс") ? (getExpToNextLevel() - baseExp) : 25;
            }
            case 5 -> {
                setExpToNextLevel(200);
                baseExp += enemy.getType().equals("Босс") ? (getExpToNextLevel() - baseExp) : 30;
            }
        }
        setValueOfExp(baseExp);
    }


    private int getCurrentLocation() {
        return currentLocation;
    }

    private void setCurrentLocation(int location) {
        this.currentLocation = location;
    }
}
