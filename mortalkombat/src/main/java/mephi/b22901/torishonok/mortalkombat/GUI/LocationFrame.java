package mephi.b22901.torishonok.mortalkombat.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LocationFrame extends JFrame {

    private JSlider slider;
    private JLabel selectedLocationsLabel;
    private JTextField nameField;

    public LocationFrame() {
        setTitle("Mortal Kombat");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Design.black_blue);

        // Панель с вертикальным расположением элементов
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Заголовки
        JLabel titleLabel1 = new JLabel("Выберите количество");
        JLabel titleLabel2 = new JLabel("локаций");
        JLabel nameLabel = new JLabel("Введите Ваше имя");


        titleLabel1.setFont(Design.font.deriveFont(36f));
        titleLabel1.setForeground(Color.WHITE);
        titleLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        titleLabel2.setFont(Design.font.deriveFont(36f));
        titleLabel2.setForeground(Color.WHITE);
        titleLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameLabel.setFont(Design.font.deriveFont(36f));;
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Подзаголовок
        JLabel subtitleLabel = new JLabel("Количество битв = количеству локаций");
        subtitleLabel.setFont(Design.font.deriveFont(18f));
        subtitleLabel.setForeground(Design.yellow);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Поле ввода имени
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(250, 40));
        nameField.setMaximumSize(new Dimension(250, 40));
        nameField.setFont(Design.font.deriveFont(18f));
        nameField.setHorizontalAlignment(JTextField.CENTER);

        // Слайдер
        slider = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);
        slider.setOpaque(false);
        slider.setForeground(Color.RED);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(1);
        slider.setSnapToTicks(true);
        slider.setMaximumSize(new Dimension(300, slider.getPreferredSize().height));

        // Отображение значения слайдера
        selectedLocationsLabel = new JLabel("Выбрано локаций: 3");
        selectedLocationsLabel.setFont(Design.font.deriveFont(18f));
        selectedLocationsLabel.setForeground(Color.WHITE);
        selectedLocationsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Кнопка "Подтвердить"
        JButton confirmButton = new JButton("Подтвердить");
        confirmButton.setFont(Design.font.deriveFont(18f));
        confirmButton.setBackground(new Color(0, 150, 0));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);
        confirmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Эффект при наведении
        confirmButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                confirmButton.setBackground(Design.green.darker());
            }

            public void mouseExited(MouseEvent evt) {
                confirmButton.setBackground(Design.green);
            }
        });

        confirmButton.addActionListener(e -> {
            int selectedLocations = slider.getValue();
            String playerName = nameField.getText();

            if (playerName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Введите имя", "Ошибка", JOptionPane.ERROR_MESSAGE);
            } else if (!playerName.matches("[a-zA-Zа-яА-Я]+")) {
                JOptionPane.showMessageDialog(this, "Имя должно содержать только буквы", "Ошибка", JOptionPane.ERROR_MESSAGE);
            } else if (selectedLocations < 1 || selectedLocations > 5) {
                JOptionPane.showMessageDialog(this, "Введите число от 1 до 5", "Ошибка", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                dispose();
                new FrameFight(selectedLocations, playerName);
            }
        });

        slider.addChangeListener(e -> {
            int value = slider.getValue();
            selectedLocationsLabel.setText("Выбрано локаций: " + value);
        });

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(titleLabel1);
        centerPanel.add(titleLabel2);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(subtitleLabel);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(slider);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(selectedLocationsLabel);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(nameLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(nameField);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(confirmButton);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel);
        setVisible(true);
    }
}