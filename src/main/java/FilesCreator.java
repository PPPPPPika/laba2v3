import org.apache.poi.hpsf.Section;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FilesCreator {
    private final String pathDirectory = "E:\\fileStorage\\";
    private static Map<LengthInformation, Integer> mapCounters = new HashMap<>(Map.of(LengthInformation.SHORT, 0,
                                                                                      LengthInformation.MIDDLE, 0,
                                                                                      LengthInformation.LONG, 0));
    public FilesCreator(){}

    private void createDirectory(String pathDirectory){
        if (!Files.exists(Path.of(pathDirectory))){
            new File(pathDirectory).mkdirs();
        }
    }

    public String createPath(LengthInformation lengthInformation){
        switch (lengthInformation) {
            case SHORT -> {
                createDirectory(pathDirectory + "shortTexts");
                return pathDirectory + "shortTexts" + "\\" + lengthInformation + "_file" + mapCounters.get(lengthInformation);
            }
            case MIDDLE -> {
                createDirectory(pathDirectory + "middleTexts");
                return pathDirectory + "middleTexts" + "\\" + lengthInformation + "_file" + mapCounters.get(lengthInformation);
            }
            case LONG -> {
                createDirectory(pathDirectory + "longTexts");
                return pathDirectory + "longTexts" + "\\" + lengthInformation + "_file" + mapCounters.get(lengthInformation);
            }
        }
        return null;
    }

    public void createFile(String information, String url, LengthInformation lengthInformation, int numberWords){
        String pathFile = createPath(lengthInformation);
        if (pathFile != null){
            if (!information.equals("") && !information.equals(" ") && information != null){
                try (XWPFDocument document = new XWPFDocument()){
                    XWPFParagraph additionParagraph = document.createParagraph();
                    XWPFRun additionRun = additionParagraph.createRun();
                    additionRun.setText(information);
                    try (FileOutputStream fileOutputStream = new FileOutputStream(pathFile  + ".docx")){
                        document.write(fileOutputStream);
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                createAdditionFile(numberWords, url, pathFile);
                mapCounters.replace(lengthInformation, mapCounters.get(lengthInformation), mapCounters.get(lengthInformation) + 1);
            }
        }
    }

    public void createAdditionFile(int numberWords, String url, String pathFile){
        String additionInformation = "Количество слов: " + numberWords + " | " +
                                     "Основная тематика: Экономика и финансы" + " | " +
                                     "Источник: ";
        try (XWPFDocument document = new XWPFDocument()){
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(additionInformation);
            run.addBreak();
            run.setText(url);
            try (FileOutputStream fileOutputStream = new FileOutputStream(pathFile + "_addition.docx")){
                document.write(fileOutputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkNumbersFiles(LengthInformation lengthInformation){
        boolean isBorder = false;
        if (mapCounters.get(lengthInformation) == 100)
            isBorder = true;
        return isBorder;
    }

    public boolean checkNumbersAllFiles(){
        boolean finish = false;
        if (mapCounters.get(LengthInformation.SHORT) == 100
                && mapCounters.get(LengthInformation.MIDDLE) == 100
                && mapCounters.get(LengthInformation.LONG) == 100){
            finish = true;
        }
        return finish;
    }
}
