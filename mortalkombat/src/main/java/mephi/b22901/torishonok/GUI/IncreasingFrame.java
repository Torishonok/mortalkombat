package mephi.b22901.torishonok.GUI;


//import gui.fightWindow.FightPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import mephi.b22901.torishonok.GUI.fighter.Player;

public class IncreasingFrame extends JFrame {

    private final FightPanel fightPanel;
    private final Player player;

    public IncreasingFrame(Player player, FightPanel fightPanel) {
        this.player = player;
        this.fightPanel = fightPanel;

        // Настройка окна
        setTitle("Выбор улучшения");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Design.black_blue);

        // Заголовок
        JLabel titleLabel = new JLabel("Выберите, что хотите улучшить", SwingConstants.CENTER);
        titleLabel.setFont(Design.font.deriveFont(28f));
        titleLabel.setForeground(Color.WHITE);

        // Кнопки
        JButton damageButton = new JButton("Увеличить урон");
        damageButton.setBackground(Design.red);
        JButton healthButton = new JButton("Увеличить здоровье");
        healthButton.setBackground(Design.blue);

        // Стилизуем кнопки
        styleButton(damageButton);
        styleButton(healthButton);

        // Добавляем эффекты при наведении
        addHoverEffect(damageButton, Design.red);
        addHoverEffect(healthButton, Design.blue);

        // Обработчики событий
        damageButton.addActionListener(this::increaseDamage);
        healthButton.addActionListener(this::increaseHealth);

        // Панель для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(damageButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(healthButton);

        // Основная панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Design.black_blue);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(buttonPanel);

        // Скрываем FightPanel
        fightPanel.setVisible(false);

        // Добавляем обработчик закрытия окна
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fightPanel.setVisible(true);
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    // Метод стилизации кнопок
    private void styleButton(JButton button) {
        button.setFont(Design.font.deriveFont(22f));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 60));
    }

    // Эффекты при наведении
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

    // Обработчики действий
    private void increaseDamage(ActionEvent e) {
        player.setDamage(player.getDamage() + 20);
        closeWindow();
    }

    private void increaseHealth(ActionEvent e) {
        player.setMaxHp(player.getMaxHp() + 20);
        closeWindow();
    }

    private void closeWindow() {
        setVisible(false);
        dispose();
        fightPanel.setVisible(true);
    }
}