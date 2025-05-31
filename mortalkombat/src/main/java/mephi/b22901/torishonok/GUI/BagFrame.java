package mephi.b22901.torishonok.GUI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mephi.b22901.torishonok.GUI.fighter.Enemy;
import mephi.b22901.torishonok.GUI.fighter.Player;
import mephi.b22901.torishonok.GUI.fighter.items.BagOfItems;

public class BagFrame extends JFrame {

    private final Player player;
    private final BagOfItems bagOfItems;
    private final Enemy enemy;

    // Радиокнопки для выбора предметов
    private JRadioButton rb_SmallPotion;
    private JRadioButton rb_BigPotion;
    private JRadioButton rb_CrossOfRevival;
    

    public BagFrame(Player player, Enemy enemy,  BagOfItems bagOfItems, FightPanel fightPanel) {
        this.player = player;
        this.enemy = enemy;
        this.bagOfItems = bagOfItems;

        // Настройка окна
        setTitle("Инвентарь");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Design.black_blue);

        // Заголовок
        JLabel titleLabel = new JLabel("Выберите предмет", SwingConstants.CENTER);
        titleLabel.setFont(Design.font.deriveFont(34f));
        titleLabel.setForeground(Color.WHITE);

        // Создаем радиокнопки
        rb_SmallPotion = new JRadioButton("Малое зелье лечения: " + bagOfItems.getSmallPotionCount());
        rb_BigPotion = new JRadioButton("Большое зелье лечения: " + bagOfItems.getBigPotionCount());
        rb_CrossOfRevival = new JRadioButton("Крест возрождения: " + bagOfItems.getCrossOfRevivalCount());

        styleRadioButton(rb_SmallPotion);
        styleRadioButton(rb_BigPotion);
        styleRadioButton(rb_CrossOfRevival);

        // Группируем радиокнопки
        ButtonGroup group = new ButtonGroup();
        group.add(rb_SmallPotion);
        group.add(rb_BigPotion);
        group.add(rb_CrossOfRevival);

        // Панель с радиокнопками
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.setOpaque(false);
        radioPanel.add(rb_SmallPotion);
        radioPanel.add(Box.createVerticalStrut(10));
        radioPanel.add(rb_BigPotion);
        radioPanel.add(Box.createVerticalStrut(10));
        radioPanel.add(rb_CrossOfRevival);

        // Кнопка "Применить" — центрируем её
        JButton applyButton = new JButton("Применить");
        styleButton(applyButton);
        addHoverEffect(applyButton, Design.green);

        // Панель для кнопки (гарантирует центрирование)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(applyButton);

        // Обработчик событий
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_SmallPotion.isSelected()) {
                    if (bagOfItems.getSmallPotionCount() == 0) {
                        JOptionPane.showMessageDialog(BagFrame.this,
                                "У Вас нет малого зелья лечения",
                                "Ошибка",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        bagOfItems.useItem("Малое зелье лечения", player);
                        rb_SmallPotion.setText("Малое зелье лечения: " + bagOfItems.getSmallPotionCount());
                    }

                } else if (rb_BigPotion.isSelected()) {
                    if (bagOfItems.getBigPotionCount() == 0) {
                        JOptionPane.showMessageDialog(BagFrame.this,
                                "У Вас нет большого зелья лечения",
                                "Ошибка",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        bagOfItems.useItem("Большое зелье лечения", player);
                        rb_BigPotion.setText("Большое зелье лечения: " + bagOfItems.getBigPotionCount());
                    }
                } else if (rb_CrossOfRevival.isSelected()) {
                    if (bagOfItems.getCrossOfRevivalCount() == 0) {
                        JOptionPane.showMessageDialog(BagFrame.this,
                                "У Вас нет креста возрождения",
                                "Ошибка",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        bagOfItems.useItem("Крест возрождения", player);
                        rb_CrossOfRevival.setText("Крест возрождения: " + bagOfItems.getCrossOfRevivalCount());
                    }
                } else {
                    JOptionPane.showMessageDialog(BagFrame.this,
                            "Выберите предмет для применения",
                            "Ошибка",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                fightPanel.changeAllLabels(player, enemy);
            }
        });

        // Основная панель с вертикальным расположением
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Design.black_blue);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Добавляем элементы
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(radioPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel); // Здесь кнопка точно по центру

        add(mainPanel);
        setVisible(true);
    }

    // Стилизация радиокнопок
    private void styleRadioButton(JRadioButton radioButton) {
        radioButton.setFont(Design.font.deriveFont(20f));
        radioButton.setForeground(Color.WHITE);
        radioButton.setBackground(Design.black_blue);
        radioButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    // Стилизация кнопок
    private void styleButton(JButton button) {
        button.setFont(Design.font.deriveFont(24f));
        button.setBackground(Design.green);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    // Эффект при наведении на кнопку
    private void addHoverEffect(JButton button, Color baseColor) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(baseColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(baseColor);
            }
        });
    }
}
