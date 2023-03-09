package main.com.example.obj.types;

import java.util.Locale;
import java.util.Objects;

public class Face implements  IType{
    public final int MinimumDataLength = 4;
    public final String Prefix = "f";

    private int[] vertexIndexList;
    private int[] textureVertexIndexList;
    private int[] normalsVertexIndexList;

    @Override
    public void ReadFromStringArray(String[] data) throws IllegalArgumentException {
        if (data.length < MinimumDataLength)
            throw new IllegalArgumentException("Длина входных данных должна быть не меньше " + MinimumDataLength);

        if (!data[0].equalsIgnoreCase(Prefix))
            throw new IllegalArgumentException("Префикс должен быть '" + Prefix + "'");

        int vcount = data.length - 1;
        vertexIndexList = new int[vcount];
        textureVertexIndexList = new int[vcount];
        normalsVertexIndexList = new int[vcount];

        for (int i = 0; i < vcount; i++) {
            String[] parts = data[i + 1].split("/");

            int vindex;
            try{
                vindex = Integer.parseInt(parts[0]);
            }catch (NumberFormatException e){
                throw new IllegalArgumentException("Не удается распарсить int");
            }
            vertexIndexList[i] = vindex;

            if (parts.length > 1) {
                try{
                    vindex = Integer.parseInt(parts[1]);
                    textureVertexIndexList[i] = vindex;
                }catch (NumberFormatException ignored){
                    if(!Objects.equals(parts[1], "")){
                        throw new IllegalArgumentException("Не удается распарсить int");
                    }
                }
                if(parts.length > 2) {
                    try {
                        vindex = Integer.parseInt(parts[2]);
                        normalsVertexIndexList[i] = vindex;
                    } catch (NumberFormatException ignored) {
                        throw new IllegalArgumentException("Не удается распарсить int");
                    }
                }
            }
        }
    }

    public int[] getVertexIndexList() {
        return vertexIndexList;
    }

    public void setVertexIndexList(int[] vertexIndexList) {
        this.vertexIndexList = vertexIndexList;
    }

    public int[] getTextureVertexIndexList() {
        return textureVertexIndexList;
    }

    public void setTextureVertexIndexList(int[] textureVertexIndexList) {
        this.textureVertexIndexList = textureVertexIndexList;
    }

    public int[] getNormalsVertexIndexList() {
        return normalsVertexIndexList;
    }

    public void setNormalsVertexIndexList(int[] normalsVertexIndexList) {
        this.normalsVertexIndexList = normalsVertexIndexList;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("f");

        for (int i = 0; i < vertexIndexList.length; i++)
        {
            if (i < normalsVertexIndexList.length) {
                b.append(String.format(Locale.US, " %d/%d/%d",
                        vertexIndexList[i], textureVertexIndexList[i], normalsVertexIndexList[i]));
            }
            else if (i < textureVertexIndexList.length)
            {
                b.append(String.format(Locale.US, " %d/%d", vertexIndexList[i], textureVertexIndexList[i]));
            }
            else
            {
                b.append(String.format(Locale.US, " %d", vertexIndexList[i]));
            }
        }

        return b.toString();
    }
}
