package main.com.example.obj;

import main.com.example.obj.types.Face;
import main.com.example.obj.types.TextureVertex;
import main.com.example.obj.types.Vertex;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ObjReader {
    public ArrayList<Vertex> VertexList;
    public ArrayList<Face> FaceList;
    public ArrayList<TextureVertex> TextureList;

    // Конструктор
    public ObjReader()
    {
        VertexList = new ArrayList<>();
        FaceList = new ArrayList<>();
        TextureList = new ArrayList<>();
    }

    // Загружаем .obj из пути к файлу
    public void LoadObj(String path) throws IOException {
        LoadObjFromArray((ArrayList<String>) Files.readAllLines(Path.of(path)));
    }

    // Загружаем .obj из стрима
    public void LoadObjFromStream(BufferedReader reader) throws IOException {
        ArrayList<String> data = new ArrayList<>();
        while(reader.ready()) {
            String line = reader.readLine();
            data.add(line);
        }
        LoadObjFromArray(data);
    }

    // Загружаем .obj из массива строк
    public void LoadObjFromArray(ArrayList<String> data)
    {
        for(String line : data)
        {
            processLine(line);
        }
    }

    // Читает и разбирает строку из OBJ файла
    // Поддерживаются V, VT, F префиксы
    private void processLine(String line)
    {
        String[] parts = line.split(" ");

        if (parts.length > 0)
        {
            switch (parts[0]) {
                case "v" -> {
                    Vertex v = new Vertex();
                    v.ReadFromStringArray(parts);
                    VertexList.add(v);
                    v.setIndex(VertexList.size());
                }
                case "f" -> {
                    Face f = new Face();
                    f.ReadFromStringArray(parts);
                    FaceList.add(f);
                }
                case "vt" -> {
                    TextureVertex vt = new TextureVertex();
                    vt.ReadFromStringArray(parts);
                    TextureList.add(vt);
                    vt.setIndex(TextureList.size());
                }
            }
        }
    }
}
