package main.com.example.obj;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // проверяем работу программы с помощью записи и чтения файла

        // это данные которые будут в файле
        String[] objFileArray = new String[]
        {
                "v -0.500000 -0.500000 0.500000",
                "v 0.500000 -0.500000 0.500000",
                "v -0.500000 0.500000 0.500000",
                "v 0.500000 0.500000 0.500000",
                "vt 5.071100 0.000300",
                "vt 5.461200 1.000000",
                "f 1/1/1 2/2/1 3/3/1",
                "f 1/1/1 2/2/1 3/3/1"
        };
        // это файл представленный в виде строк
        ArrayList<String> objFile = new ArrayList<>(Arrays.asList(objFileArray));
        // читаем файл
        ObjReader reader = new ObjReader();
        reader.LoadObjFromArray(objFile);

        // записываем файл
        ObjWriter writer = new ObjWriter(reader.VertexList, reader.FaceList, reader.TextureList);
        String[] headers = new String[] {};
        try {
            writer.WriteObjFile("test.obj", headers);
        } catch (IOException e) {
            System.out.println("Не удалось записать файл");
        }

        Path filePath = Path.of("test.obj");
        String fileContent = "";

        // проверяем записанный файл
        try {
            // читаем весь файл в строку
            byte[] bytes = Files.readAllBytes(filePath);
            fileContent = new String (bytes);
            // объединяем массив строк в одну строку
            String initialContent = String.join(System.lineSeparator(), objFile);
            // добавляем заверщающий перевод строки
            initialContent += System.lineSeparator();
            // сравниваем начальную и прочитанную строки
            if(fileContent.equals(initialContent)){
                System.out.println("Obj записан успешно");
            }else{
                System.out.println("Не удалось записать Obj");
            }
        } catch (IOException ignored) {
            System.out.println("Не удалось прочитать файл");
        }
    }
}