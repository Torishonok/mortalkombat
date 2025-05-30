package mephi.b22901.torishonok.mortalkombat.GUI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import mephi.b22901.torishonok.mortalkombat.GUI.fighter.Enemy;
import mephi.b22901.torishonok.mortalkombat.GUI.fighter.Entity;
import mephi.b22901.torishonok.mortalkombat.GUI.fighter.Fight;
import mephi.b22901.torishonok.mortalkombat.GUI.fighter.Player;
import mephi.b22901.torishonok.mortalkombat.GUI.fighter.items.BagOfItems;
import mephi.b22901.torishonok.mortalkombat.Location;

public class FightPanel extends JPanel{

    Player player = new Player();
    Fight fight = new Fight();

    private boolean flagDebag = false;

    private BagOfItems bagOfItems = new BagOfItems();

    private final String playerName;

    private final int locationNumber;
    private int numberDefeatedEnemies = 0; //количество поверженных врагов

    //    Панели нижнего уровня
    private final JPanel p_firstLevel = new JPanel();
    private final JPanel p_secondLevel = new JPanel();
    private final JPanel p_thirdLevel = new JPanel();
    private final BorderLayout borderLayout = new BorderLayout();
    // Заполнение верхней панели
    private final JLabel title = new JLabel("Fight");
    // Заполнение центральной панели, 2 ряд
    private final JPanel p_secondRow = new JPanel();

    private final JPanel p_enemyInfo = new JPanel();
    private final JLabel l_enemyHp = new JLabel();
    private final JProgressBar pr_enemyHp = new JProgressBar();
    private final JLabel l_enemyDamage = new JLabel("Damage");

    private final JPanel p_pointsExp = new JPanel();
    private final JLabel l_points = new JLabel("Очки");
    private final JLabel l_experience = new JLabel("Опыт");
    private final JLabel l_valuePoints = new JLabel("");
    private final JLabel l_valueExperience = new JLabel("");

    private final JPanel p_playerInfo = new JPanel();
    private final JLabel l_playerHp = new JLabel(player.getCurrentHp() + "/" + player.getMaxHp());
    private final JProgressBar pr_playerHp = new JProgressBar();
    private final JLabel l_playerDamage = new JLabel("Урон");

    // Заполнение центральной панели, 3 ряд
    private final JPanel p_thirdRow = new JPanel();

    private final JPanel p_enemyImage = new JPanel();
    private final JLabel l_enemyLevel = new JLabel();
    private final JLabel l_enemyName = new JLabel();

    private final JPanel p_center = new JPanel();

    private final JPanel p_action = new JPanel();
    private final JLabel l_actionPlayer = new JLabel();
    private final JLabel l_actionEnemy = new JLabel();

    private final JPanel p_turn = new JPanel();
    private final JLabel l_turn = new JLabel();
    private final JLabel l_stun = new JLabel();

    private final JPanel p_playerImage = new JPanel();
    private final JLabel l_playerLevel = new JLabel("уровень " + player.getLevel());
    private final JLabel l_playerName;

    // Заполнение нижней панели
    private final JPanel p_buttons = new JPanel();
    private final JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
    private final JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    private final JButton b_attack = new JButton("Атака");
    private final JButton b_defend = new JButton("Защита");
    private final JButton b_debaff = new JButton("Дебафф");
    private final JButton b_items = new JButton("Мешок");
    private final JButton b_debug = new JButton("Режим отладки");
            
    private int number_location = 1;
    private Location location = new Location(number_location);
    private Enemy enemy;

    Dimension buttonSize = new Dimension(150, 50);

    private boolean statusStep = true;

    JLabel scaledEnemyImage;
    JLabel scaledPlayerImageOriginal = new JLabel(scaleImageIcon(new ImageIcon("src/main/resources/player.png"), 300, 400));
    JLabel scaledPlayerImage =  scaledPlayerImageOriginal;
    
    private Image locatonImage = Location.getLocationImage(1);


    public FightPanel(int locationCount, String playerName) {

        this.playerName = playerName;
        l_playerName = new JLabel(playerName);
        this.locationNumber = locationCount;
        
        //setBackground(Design.black_blue);
        
        setOpaque(false);
        
        checkLocation();

        changeAllLabels(player, enemy);

        setLayout(borderLayout);
        add(p_firstLevel, BorderLayout.NORTH);
        add(p_secondLevel, BorderLayout.CENTER);
        add(p_thirdLevel, BorderLayout.SOUTH);

        title.setFont(Design.font.deriveFont(56f));
        title.setForeground(Design.red);
        p_firstLevel.add(title);

        p_secondLevel.setLayout(new BoxLayout(p_secondLevel, BoxLayout.Y_AXIS));
        p_secondLevel.add(Box.createVerticalStrut(10));

        p_secondRow.setPreferredSize(new Dimension(p_secondLevel.getWidth(), 100));
        p_secondRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        p_secondRow.setLayout(new GridLayout(1, 3));

        p_enemyInfo.setLayout(new GridLayout(2, 2));
        p_enemyInfo.add(wrapInPanel(l_enemyHp));
        p_enemyInfo.add(wrapInPanel(pr_enemyHp));
        p_enemyInfo.add(wrapInPanel(l_enemyDamage));

        p_pointsExp.setLayout(new GridLayout(2, 2));
        p_pointsExp.add(wrapInPanel(l_points));
        p_pointsExp.add(wrapInPanel(l_experience));
        p_pointsExp.add(wrapInPanel(l_valuePoints));
        p_pointsExp.add(wrapInPanel(l_valueExperience));

        p_playerInfo.setLayout(new GridLayout(2, 2));
        p_playerInfo.add(wrapInPanel(l_playerHp));
        p_playerInfo.add(wrapInPanel(pr_playerHp));
        p_playerInfo.add(wrapInPanel(l_playerDamage));

        p_secondRow.add(p_enemyInfo);
        p_secondRow.add(p_pointsExp);
        p_secondRow.add(p_playerInfo);

        p_thirdRow.setLayout(new GridLayout(1, 3));

        p_enemyImage.setLayout(new BorderLayout());
        p_enemyImage.add(l_enemyLevel, BorderLayout.NORTH);
        p_enemyImage.add(scaledEnemyImage, BorderLayout.CENTER);
        p_enemyImage.add(wrapInPanel(l_enemyName), BorderLayout.SOUTH);

        p_center.setLayout(new GridLayout(2, 1));

        p_action.setLayout(new BoxLayout(p_action, BoxLayout.Y_AXIS));
        p_action.add(wrapInPanel(l_actionPlayer));
        p_action.add(wrapInPanel(l_actionEnemy));

        p_turn.setLayout(new GridLayout(2, 1));
        p_turn.add(wrapInPanel(l_turn));
        p_turn.add(wrapInPanel(l_stun));

        p_center.add(p_action);
        p_center.add(p_turn);

        p_playerImage.setLayout(new BorderLayout());
        p_playerImage.add(rightAlignInPanel(l_playerLevel), BorderLayout.NORTH);
        p_playerImage.add(scaledPlayerImage, BorderLayout.CENTER);
        p_playerImage.add(wrapInPanel(l_playerName), BorderLayout.SOUTH);

        p_thirdRow.add(p_enemyImage);
        p_thirdRow.add(p_center);
        p_thirdRow.add(p_playerImage);

        p_secondLevel.add(p_secondRow);
        p_secondLevel.add(p_thirdRow);

        b_attack.setFont(Design.font);
        b_defend.setFont(Design.font);
        b_debaff.setFont(Design.font);
        b_items.setFont(Design.font);
        b_debug.setFont(Design.font);

        b_attack.setPreferredSize(buttonSize);
        b_attack.setBackground(Color.RED);
        b_attack.setForeground(Color.WHITE);
        
        b_defend.setPreferredSize(buttonSize);
        b_defend.setBackground(Design.green);
        b_defend.setForeground(Color.WHITE);
        
        b_debaff.setPreferredSize(buttonSize);
        b_debaff.setForeground(Color.WHITE);
        b_debaff.setBackground(Design.blue);
        
        b_items.setPreferredSize(buttonSize);
        b_items.setForeground(Color.WHITE);
        b_items.setBackground(Design.purple);
        Dimension debugButtonSize = new Dimension(300, 50);
        b_debug.setPreferredSize(debugButtonSize);
        b_debug.setForeground(Color.WHITE);
        b_debug.setBackground(Design.yellow);
                
        leftPanel.add(b_debug);
        rightPanel.add(b_items);
        rightPanel.add(b_attack);
        rightPanel.add(b_defend);
        rightPanel.add(b_debaff);

        p_buttons.setLayout(new BorderLayout());
        p_buttons.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 50));
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        leftPanel.add(b_debug);

        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 200, 10, 0));
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.add(b_items);
        rightPanel.add(b_attack);
        rightPanel.add(b_defend);
        rightPanel.add(b_debaff);

        p_buttons.add(leftPanel, BorderLayout.WEST);
        p_buttons.add(rightPanel, BorderLayout.EAST);

        p_thirdLevel.add(p_buttons);

        setLabelFont(Design.font);

        b_attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                l_actionPlayer.setText("Атакует игрок");
                player.setMoveStatus(Entity.MoveStatus.ATTACK);
                if (player.getFlagDebaff()) {
                    player.setFlagDebaff(false);
                    enemy.setCurrentHp(enemy.getCurrentHp() - (int) (player.getDamage() * 1.15));
                    System.out.println("Debuf snyat u Player");
                }
                fight.doFight(player, enemy, statusStep);
                changeAllLabels(player, enemy);
                if (statusStep) {
                    statusStep = false;
                } else {
                    statusStep = true;
                }
                enemyZeroHp();
            }
        });

        b_defend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                player.setMoveStatus(Entity.MoveStatus.DEFENDING);
                l_actionPlayer.setText("Игрок защищается");
                fight.doFight(player, enemy, statusStep);
                changeAllLabels(player, enemy);
                if (statusStep) {
                    statusStep = false;
                } else {
                    statusStep = true;
                }
                enemyZeroHp();
            }
        });

        b_debaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                l_actionPlayer.setText("Игрок ослабляет противника");
                player.setMoveStatus(Entity.MoveStatus.DEBAFF);
                //applyDebuffIfActive(); 
                fight.doFight(player, enemy, statusStep);
                changeAllLabels(player, enemy);
                if (statusStep) {
                    statusStep = false;
                } else {
                    statusStep = true;
                }
                enemyZeroHp();
            }
        });

        b_items.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BagFrame(player, enemy, bagOfItems, FightPanel.this);
                changeAllLabels(player, enemy);
            }
        });

        b_debug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flagDebag = true;
                player.setMaxHp(5000);
                player.setCurrentHp(5000);
                player.setDamage(5000);
                changeAllLabels(player, enemy);
            }
        });
    }

    private void checkLocation() {
        if (location.getListEnemy().size() != 0) {
            enemy = location.getListEnemy().get(0);
            enemy.setLevel(player.getLevel());
        } else {
            number_location++;
            if (number_location <= locationNumber) {
                location = new Location(number_location);
                enemy = location.getListEnemy().get(0);
                enemy.setLevel(player.getLevel());
                new IncreasingFrame(player, FightPanel.this);
                player.setCurrentHp(player.getMaxHp());
                changeImageLocation(number_location);
                changeAllLabels(player, enemy);

                revalidate();
                repaint();
            } else {
                new FinishTableFrame(playerName, player.getPoints());

                b_attack.setEnabled(false);
                b_debaff.setEnabled(false);
                b_defend.setEnabled(false);
                b_debug.setEnabled(false);
            }
        }
    }

    private void enemyZeroHp() {
        if (enemy.getCurrentHp() <= 0) {
            bagOfItems.dropItem();
            player.calculatePoints(player.getCurrentHp(), player.getMaxHp());
            player.calculateExp(enemy, number_location);
            location.getListEnemy().remove(enemy);
            numberDefeatedEnemies += 1;
            player.calculatePlayerLevel(numberDefeatedEnemies, flagDebag);
            checkLocation();
            changeAllLabels(player, enemy);

        } else if(player.getCurrentHp() <= 0){
            if (bagOfItems.getCrossOfRevivalCount() == 0) {
                b_items.setEnabled(false);
                b_attack.setEnabled(false);
                b_debaff.setEnabled(false);
                b_defend.setEnabled(false);
                b_debug.setEnabled(false);
                changeAllLabels(player, enemy);
                new FinishTableFrame(playerName, player.getPoints());
                
                
            } else {
                bagOfItems.autoUsingCross(player);
                changeAllLabels(player, enemy);
                JOptionPane.showMessageDialog(this, "Крест возрождения применен", "Информация", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private JPanel wrapInPanel(JComponent component) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(component);
        return panel;
    }

    private JPanel rightAlignInPanel(JComponent component) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(component);
        return panel;
    }

    private ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    public  void changeAllLabels(Player player, Enemy enemy) {
        changePlayerInfo(player);
        changeEnemyInfo(enemy);
        }
    
    private void changeImageLocation(int locationID) {
        locatonImage = Location.getLocationImage(locationID);
        repaint();
    }

    private void changePlayerInfo(Player player) {
        l_valuePoints.setText(String.valueOf(player.getPoints()));
        l_valueExperience.setText(player.getValueOfExp() + "/" + player.getExpToNextLevel());
        if (player.getCurrentHp() <= 0) {
        l_playerHp.setText(0 + "/" + player.getMaxHp());
        } else {l_playerHp.setText(player.getCurrentHp() + "/" + player.getMaxHp());}
        l_playerDamage.setText("Урон: " + player.getDamage());
        l_playerLevel.setText(player.getLevel() + " уровень");
        if (statusStep) {l_turn.setText("Ваш ход");}
        if (player.getMoveStatus() == Entity.MoveStatus.STUNNING) {
            scaledPlayerImage = new JLabel(withRedBackground(scaleImageIcon(new ImageIcon("src/main/resources/player.png"), 300, 400)));
            l_stun.setText("Вы оглушены");
        } else {
            l_stun.setText("");
            scaledPlayerImage = scaledPlayerImageOriginal;
        }

        l_stun.setVisible(true);
        pr_playerHp.setMaximum(player.getMaxHp());
        pr_playerHp.setValue(player.getCurrentHp());
        revalidate();
        repaint();
    }

    private void changeEnemyInfo(Enemy enemy) {
        if (enemy.getCurrentHp() <= 0) {
            l_enemyHp.setText(0 + "/" + enemy.getMaxHp());
        } else { l_enemyHp.setText(enemy.getCurrentHp() + "/" + enemy.getMaxHp());}
        l_enemyDamage.setText("Урон: " + enemy.getDamage());
        l_enemyLevel.setText(enemy.getLevel() + " уровень");
        l_enemyName.setText(enemy.getName() + " (" + enemy.getType() + ")");
        if (!statusStep) {l_turn.setText("Ход врага");}
        if (enemy.getMoveStatus() == Entity.MoveStatus.STUNNING) {l_stun.setText("Враг оглушен");} else {l_stun.setText("");}
        
        
        ImageIcon originalIcon = new ImageIcon(enemy.getPhotoPath());

        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        if (enemy.getMoveStatus() == Entity.MoveStatus.STUNNING) {
            scaledIcon = withRedBackground(scaledIcon);
        }

        if (scaledEnemyImage != null) {
            scaledEnemyImage.setIcon(scaledIcon);
        } else {
            scaledEnemyImage = new JLabel(scaledIcon);
            add(scaledEnemyImage);
        }

        pr_enemyHp.setMaximum(enemy.getMaxHp());

        pr_enemyHp.setValue(enemy.getCurrentHp());

        revalidate();
        repaint();

        actionEnemy(enemy);
    }

    private void actionEnemy(Enemy enemy) {
        if (enemy.getMoveStatus() == Enemy.MoveStatus.ATTACK) {
            l_actionEnemy.setText("Враг атакует");
        } else if (enemy.getMoveStatus() == Enemy.MoveStatus.DEFENDING) {
            l_actionEnemy.setText("Враг защищается");
        } else if (enemy.getMoveStatus() == Enemy.MoveStatus.DEBAFF) {
            l_actionEnemy.setText("Враг дебаффит");
        }
    }

    private void setLabelFont(Font font) {
        l_enemyHp.setFont(font);
        l_enemyHp.setForeground(Design.purple);
        l_enemyDamage.setFont(font);
        l_enemyDamage.setForeground(Design.purple);
        l_points.setFont(font);
        l_points.setForeground(Color.WHITE);
        l_experience.setFont(font);
        l_experience.setForeground(Color.WHITE);
        l_valuePoints.setFont(font);
        l_valueExperience.setForeground(Color.WHITE);
        l_valueExperience.setFont(font);
        l_valuePoints.setForeground(Color.WHITE);
        l_playerHp.setFont(font);
        l_playerHp.setForeground(Design.purple);
        l_playerDamage.setFont(font);
        l_playerDamage.setForeground(Design.purple);
        l_enemyLevel.setFont(font);
        l_enemyLevel.setForeground(Color.WHITE);
        l_enemyName.setFont(font);
        l_enemyName.setForeground(Color.WHITE);
        l_actionPlayer.setFont(font);
        l_actionPlayer.setForeground(Color.WHITE);
        l_turn.setFont(font);
        l_turn.setForeground(Design.yellow);
        l_stun.setFont(font);
        l_stun.setForeground(Color.WHITE);
        l_playerLevel.setFont(font);
        l_playerLevel.setForeground(Color.WHITE);
        l_playerName.setFont(font);
        l_playerName.setForeground(Color.WHITE);
        l_actionEnemy.setFont(font);
        l_actionEnemy.setForeground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (locatonImage != null) {
            g.drawImage(locatonImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public ImageIcon withRedBackground(ImageIcon icon) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = out.createGraphics();

        // Полупрозрачный красный фон
        g2.setColor(new Color(255, 0, 0, 128));
        g2.fillRect(0, 0, w, h);

        // Исходная картинка сверху
        g2.drawImage(icon.getImage(), 0, 0, w, h, null);
        g2.dispose();

        return new ImageIcon(out);
    }

}
