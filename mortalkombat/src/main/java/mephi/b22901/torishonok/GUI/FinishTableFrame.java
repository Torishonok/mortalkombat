package mephi.b22901.torishonok.GUI;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;
import mephi.b22901.torishonok.result.ExcelWriter;
import mephi.b22901.torishonok.result.Result;

public class FinishTableFrame extends JFrame {
    
    private ExcelWriter excelManager = new ExcelWriter();
    private JTable table;
    private DefaultTableModel tableModel;

    public FinishTableFrame(String playerName, int points) {
        setTitle("Таблица рекордов");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setBackground(Design.black_blue);

        // Проверка и запись результата
        boolean isInTop10 = excelManager.writeScore(new Result(playerName, points));

        if (!isInTop10) {
            JOptionPane.showMessageDialog(this,
                    "Вы не попали в топ-10",
                    "Информация",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        // Создаем таблицу
        List<Result> scores = excelManager.readScores();
        initTable(scores);

        // Добавляем таблицу с прокруткой
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Заголовок
        JLabel titleLabel = new JLabel("Таблица рекордов", SwingConstants.CENTER);
        titleLabel.setFont(Design.font);
        titleLabel.setForeground(Color.WHITE);

        // Кнопка "Назад"
        JButton backButton = new JButton("Назад");
        backButton.setFont(Design.font);
        backButton.setBackground(Design.red);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Эффект при наведении
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(Design.red.darker());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(Design.red);
            }
        });

        // Обработчик кнопки
        backButton.addActionListener(e -> {
            setVisible(false);
        });

        // Панель для кнопки
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);

        // Основная панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(scrollPane);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(buttonPanel);

        add(mainPanel);
        setVisible(true);
    }

    private void initTable(List<Result> scores) {
        tableModel = new DefaultTableModel(new Object[]{"Имя", "Очки"}, 0);
        table = new JTable(tableModel);

        Font tableFont = Design.font.deriveFont(22f);
        table.setFont(tableFont);
        table.setRowHeight(30);
        table.setGridColor(Design.black_blue);
        table.setIntercellSpacing(new Dimension(10, 5));

        JTableHeader header = table.getTableHeader();
        header.setFont(Design.font);
        header.setBackground(Design.blue);

        // Ограничиваем вывод 10 строками
        for (int i = 0; i < Math.min(scores.size(), 10); i++) {
            Result score = scores.get(i);
            tableModel.addRow(new Object[]{score.getName(), score.getPoints()});
        }
    }
}