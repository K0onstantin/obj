package main.com.example.obj;

import main.com.example.obj.types.Face;
import main.com.example.obj.types.TextureVertex;
import main.com.example.obj.types.Vertex;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ObjWriter {
    public ArrayList<Vertex> VertexList;
    public ArrayList<Face> FaceList;
    public ArrayList<TextureVertex> TextureList;

    // Конструктор
    public ObjWriter(ArrayList<Vertex> vertexList, ArrayList<Face> faceList, ArrayList<TextureVertex> textureList)
    {
        VertexList = vertexList;
        FaceList = faceList;
        TextureList = textureList;
    }

    public void WriteObjFile(String path, String[] headerStrings) throws IOException {
        OutputStream os = new FileOutputStream(path);
        Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        // записываем заголовок
        WriteHeader(writer, headerStrings);

        for(Vertex v : VertexList){
            writer.write(v.toString() + System.lineSeparator());
        }

        for(TextureVertex t: TextureList){
            writer.write(t.toString() + System.lineSeparator());
        }

        for (Face face : FaceList) {
            writer.write(face.toString() + System.lineSeparator());
        }
        writer.close();
    }

    private void WriteHeader(Writer writer, String[] headerStrings) throws IOException {
        if (headerStrings == null) {
            return;
        }

        for (String line : headerStrings) {
            writer.write(line + System.lineSeparator());
        }
    }
}
