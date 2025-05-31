package mephi.b22901.torishonok.GUI.fighter;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Fight {

    private enum Mood { AGGRESSIVE, BALANCED, CAUTIOUS }
    private Map<Entity.MoveStatus, Integer> moveStats = new HashMap<>();
    private Mood currentMood = Mood.BALANCED;
    private Random random = new Random();

    public void doFight(Player player, Enemy enemy, boolean turn) {
        System.out.println("=== Turn starts ===\n turn player "+ turn);

        updatePlayerStats(player);
        determineMood();

        chooseActionBasedOnMood(player, enemy, turn);

        checkDebafEntity(player);
        checkDebafEntity(enemy);
        checkBoss(player, enemy, turn);

        System.out.println("[HP] Player: " + player.getCurrentHp() + " | Enemy: " + enemy.getCurrentHp());
        System.out.println("=== Turn ends ===\n");
    }

    private void updatePlayerStats(Player player) {
        Entity.MoveStatus move = player.getMoveStatus();
        moveStats.put(move, moveStats.getOrDefault(move, 0) + 1);
    }

    private void determineMood() {
        int attackCount = moveStats.getOrDefault(Entity.MoveStatus.ATTACK, 0);
        int defendCount = moveStats.getOrDefault(Entity.MoveStatus.DEFENDING, 0);

        if (attackCount > defendCount * 1.5) {
            currentMood = Mood.CAUTIOUS;
        } else if (defendCount > attackCount * 1.5) {
            currentMood = Mood.AGGRESSIVE;
        } else {
            currentMood = Mood.BALANCED;
        }

        System.out.println("Enemy mood: " + currentMood);
    }

    private void chooseActionBasedOnMood(Player player, Enemy enemy, boolean turn) {
        int choice = random.nextInt(100);

        switch (currentMood) {
            case AGGRESSIVE:
                if (choice < 70) {
                    System.out.println("Enemy chooses to ATTACK (Aggressive)");
                    enemyAttack(turn, player, enemy);
                } else if (choice < 90) {
                    System.out.println("Enemy chooses to DEFEND (Aggressive)");
                    enemyDef(turn, player, enemy);
                } else {
                    System.out.println("Enemy uses DEBUFF (Aggressive)");
                    applyDebuff(player, enemy);
                }
                break;

            case BALANCED:
                if (choice < 40) {
                    System.out.println("Enemy chooses to ATTACK (Balanced)");
                    enemyAttack(turn, player, enemy);
                } else if (choice < 80) {
                    System.out.println("Enemy chooses to DEFEND (Balanced)");
                    enemyDef(turn, player, enemy);
                } else {
                    System.out.println("Enemy uses DEBUFF (Balanced)");
                    applyDebuff(player, enemy);
                }
                break;

            case CAUTIOUS:
                if (choice < 20) {
                    System.out.println("Enemy chooses to ATTACK (Cautious)");
                    enemyAttack(turn, player, enemy);
                } else if (choice < 80) {
                    System.out.println("Enemy chooses to DEFEND (Cautious)");
                    enemyDef(turn, player, enemy);
                } else {
                    System.out.println("Enemy uses DEBUFF (Cautious)");
                    applyDebuff(player, enemy);
                }
                break;
        }
    }

    private void applyDebuff(Player player, Enemy enemy) {
        if ("Mag".equals(enemy.getType()) || "Маг".equals(enemy.getType())) {
            System.out.println("Enemy applies DEBUFF to the player!");
            player.setFlagDebaff(true);
            player.setTimerDebaff(enemy.getLevel());
        }
    }

    private void checkDebafEntity(Entity entity) {
        if (entity.getFlagDebaff()) {
            entity.setTimerDebaff(entity.getTimerDebaff() - 1);
            if (entity.getTimerDebaff() <= 0) {
                entity.setFlagDebaff(false);
                System.out.println(entity.getClass().getSimpleName() + " debuff expired.");
            }
        }
    }

    private void enemyAttack(boolean turn, Player player, Enemy enemy) {
        enemy.setMoveStatus(Entity.MoveStatus.ATTACK);

        if (enemy.getFlagDebaff()) {
            System.out.println("Enemy is weakened, deals extra 15% damage!");
            player.setCurrentHp(player.getCurrentHp() - (int) (enemy.getDamage() * 1.15));
        }

        if (turn && player.getMoveStatus() == Entity.MoveStatus.ATTACK) {
            System.out.println("Both attack!");
            enemy.setCurrentHp(calculateDamage(player, enemy, false));
        } else if (!turn && player.getMoveStatus() == Entity.MoveStatus.ATTACK) {
            player.setCurrentHp(calculateDamage(enemy, player, false));
        } else if (!turn && player.getMoveStatus() == Entity.MoveStatus.DEFENDING) {
            System.out.println("Player counters!");
            enemy.setCurrentHp(calculateDamage(player, enemy, true));
        } else if (player.getMoveStatus() == Entity.MoveStatus.STUNNING) {
            player.setCurrentHp(calculateDamage(enemy, player, false));
        } else if (player.getMoveStatus() == Entity.MoveStatus.DEBAFF) {
            enemy.setCurrentHp(calculateDamage(player, enemy, false));
        } else if (enemy.getMoveStatus() == Entity.MoveStatus.STUNNING) {
            enemy.setMoveStatus(Entity.MoveStatus.ATTACK);
        }
    }

    private void enemyDef(boolean turn, Player player, Enemy enemy) {
        enemy.setMoveStatus(Entity.MoveStatus.DEFENDING);

        if (turn && player.getMoveStatus() == Entity.MoveStatus.ATTACK) {
            System.out.println("Enemy counters with a defense strike!");
            player.setCurrentHp(calculateDamage(enemy, player, true));
        } else if (turn && player.getMoveStatus() == Entity.MoveStatus.DEFENDING) {
            if (random.nextBoolean()) {
                enemy.setMoveStatus(Entity.MoveStatus.STUNNING);
                System.out.println("Enemy stuns player!");
            }
        } else if (!turn && enemy.getType().equals("Mag") && player.getMoveStatus() == Entity.MoveStatus.ATTACK) {
            if (enemy.getFlagDebaff()) {
                player.setCurrentHp(player.getCurrentHp() - (int) (enemy.getDamage() * 1.15));
                enemy.setFlagDebaff(false);
                System.out.println("Enemy breaks debuff and deals extra damage!");
            }
            enemy.setCurrentHp(calculateDamage(player, enemy, false));
        } else if (player.getMoveStatus() == Entity.MoveStatus.DEBAFF) {
            int rand = random.nextInt(1, 100);
            if (rand < 75) {
                enemy.setFlagDebaff(true);
                enemy.setTimerDebaff(player.getLevel());
                System.out.println("Enemy is debuffed by player!");
            }
        } else if (!turn && player.getMoveStatus() == Entity.MoveStatus.ATTACK) {
            System.out.println("Enemy defends, nothing happens.");
        } else if (!turn && player.getMoveStatus() == Entity.MoveStatus.DEFENDING) {
            int rand = random.nextInt(1, 100);
            if (rand < 75) {
                player.setMoveStatus(Entity.MoveStatus.STUNNING);
                System.out.println("Enemy stuns player during defense!");
            }
        }
    }

    private int calculateDamage(Entity attacker, Entity defender, boolean counter) {
        int damage = attacker.getDamage();
        if (counter) {
            damage /= 2;
        }

        if (defender.getFlagDebaff()) {
            damage = (int) (damage * 1.25); // +25% урона по цели
            System.out.println("[DEBAFF] The target is weakened! Takes +25% damage");
        }

        if (attacker.getFlagDebaff()) {
            damage = damage / 2; // -50% к атаке ослабителя
            System.out.println("[DEBAFF] The attacker is weakened! Deals -50% damage");
        }

        return defender.getCurrentHp() - damage;
    }

    private void regeneration(Player player, Enemy boss) {
        boss.setCurrentHp((int) (boss.getCurrentHp() + (player.getDamage() * 0.5)));
        System.out.println("Boss regenerates HP!");
    }

    private void checkBoss(Player player, Enemy enemy, boolean turn) {
        if (enemy.getType().equals("Boss") || enemy.getType().equals("Босс")) {
            if (!turn && player.getMoveStatus() == Entity.MoveStatus.DEFENDING && random.nextInt(1, 100) > 25) {
                regeneration(player, enemy);
            }
            if (!turn && player.getMoveStatus() == Entity.MoveStatus.ATTACK) {
                enemy.setCurrentHp(calculateDamage(player, enemy, false) - enemy.getDamage());
                System.out.println("Boss interrupted regeneration and takes double damage!");
            }
        }
    }
}