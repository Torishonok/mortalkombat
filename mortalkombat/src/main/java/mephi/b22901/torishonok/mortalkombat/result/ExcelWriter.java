/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.b22901.torishonok.mortalkombat.result;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author vikus
 */

public class ExcelWriter {
    private static final String RESOURCE_PATH = "/Результаты.xlsx";
    private final String tempFilePath;

    public ExcelWriter() {
        // Создаем временный файл для записи
        this.tempFilePath = System.getProperty("java.io.tmpdir") + "/Результаты.xlsx";
        initializeTempFile();
    }

    private void initializeTempFile() {
        File tempFile = new File(tempFilePath);
        if (!tempFile.exists()) {
            try (InputStream is = getClass().getResourceAsStream(RESOURCE_PATH);
                 OutputStream os = new FileOutputStream(tempFile)) {
                if (is == null) {
                    createEmptyExcelFile(tempFile);
                } else {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка инициализации файла результатов: " + e.getMessage());
            }
        }
    }

    private void createEmptyExcelFile(File file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(file)) {
            workbook.createSheet("Scores");
            workbook.write(fos);
        }
    }

    public List<Result> readScores() {
        List<Result> scores = new ArrayList<>();

        try (InputStream is = new FileInputStream(tempFilePath);
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getCell(0) == null || row.getCell(1) == null) continue;
                
                String name = row.getCell(0).getStringCellValue();
                int points = (int) row.getCell(1).getNumericCellValue();
                scores.add(new Result(name, points));
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла результатов: " + e.getMessage());
        }
        return scores;
    }

    public boolean writeScore(Result result) {
        List<Result> scores = readScores();

        // Обработка дубликатов имен
        String originalName = result.getName();
        String newName = originalName;
        int counter = 1;

        while (isNameExist(scores, newName)) {
            newName = originalName + " (" + counter++ + ")";
        }
        result.setName(newName);

        scores.add(result);
        scores.sort((s1, s2) -> Integer.compare(s2.getPoints(), s1.getPoints()));

        boolean isInTop100 = scores.indexOf(result) < 100;
        if (scores.size() > 100) {
            scores = scores.subList(0, 100);
        }

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(tempFilePath)) {

            Sheet sheet = workbook.createSheet("Scores");
            int rowIndex = 0;
            for (Result score : scores) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(score.getName());
                row.createCell(1).setCellValue(score.getPoints());
            }

            workbook.write(fos);
            return isInTop100;
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл результатов: " + e.getMessage());
            return false;
        }
    }

    private boolean isNameExist(List<Result> scores, String name) {
        return scores.stream().anyMatch(score -> score.getName().equals(name));
    }
}