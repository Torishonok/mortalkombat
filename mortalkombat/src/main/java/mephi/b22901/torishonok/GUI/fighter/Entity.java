package mephi.b22901.torishonok.GUI.fighter;

public abstract class Entity {

    protected String name;
    protected int level;
    protected int damage;
    protected int maxHp;
    protected int currentHp;
    protected int timerDebaff;
    private boolean flagDebaff;
    //Состояние (атакует, обороняется, под дебафом, ослаблен)
    private MoveStatus moveStatus;
    private int numberStep;
    

    public Entity() {
        this.name = "";
        this.level = 0;
        this.damage = 0;
        this.maxHp = 0;
        this.currentHp = 0;
        this.timerDebaff = 0;
        this.flagDebaff = false;
        this.moveStatus = MoveStatus.ATTACK;
        this.numberStep = 1;
    }

    public int getTimerDebaff() {
        return timerDebaff;
    }

    public void setTimerDebaff(int timerDebaff) {
        this.timerDebaff = timerDebaff;
    }

    public int getNumberStep() {
        return numberStep;
    }

    public void setNumberStep(int numberStep) {
        this.numberStep = numberStep;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getLevel() {
        return level;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getFlagDebaff() {
        return flagDebaff;
    }

    public void setFlagDebaff(boolean flagDebaff) {
        this.flagDebaff = flagDebaff;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    public void setMoveStatus(MoveStatus moveStatus) {
        this.moveStatus = moveStatus;
    }

    public enum MoveStatus {
        ATTACK,
        DEFENDING,
        STUNNING, // оглушение
        DEBAFF

    }

}
