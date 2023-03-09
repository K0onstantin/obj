package test.com.example.obj;

import main.com.example.obj.types.Face;
import org.junit.Assert;
import org.junit.Test;

public class TestFace {
    @Test
    public void testReadFaceWithVertexData(){
        Face face = new Face();
        String[] array = new String[]{"f", "1", "2", "3"};
        face.ReadFromStringArray(array);

        Assert.assertArrayEquals( new int[]{1, 2, 3}, face.getVertexIndexList() );
    }

    @Test
    public void testReadFaceWithVertexAndTexturesData(){
        Face face = new Face();
        String[] array = new String[]{"f", "1/2", "2/3", "3/4"};
        face.ReadFromStringArray(array);

        Assert.assertArrayEquals( new int[]{1, 2, 3}, face.getVertexIndexList() );
        Assert.assertArrayEquals( new int[]{2,3, 4}, face.getTextureVertexIndexList() );
    }

    @Test
    public void testReadFaceWithVertexAndTexturesAndNormaleData(){
        Face face = new Face();
        String[] array = new String[]{"f", "1/2/5", "2/3/6", "3/4/7"};
        face.ReadFromStringArray(array);

        Assert.assertArrayEquals( new int[]{1, 2, 3}, face.getVertexIndexList() );
        Assert.assertArrayEquals( new int[]{2, 3, 4}, face.getTextureVertexIndexList() );
        Assert.assertArrayEquals( new int[]{5, 6, 7}, face.getNormalsVertexIndexList() );
    }

    @Test
    public void testReadFaceWithVertexAndNormaleData(){
        Face face = new Face();
        String[] array = new String[]{"f", "1//5", "2//6", "3//7"};
        face.ReadFromStringArray(array);

        Assert.assertArrayEquals( new int[]{1, 2, 3}, face.getVertexIndexList() );
        Assert.assertArrayEquals( new int[]{0, 0, 0}, face.getTextureVertexIndexList() );
        Assert.assertArrayEquals( new int[]{5, 6, 7}, face.getNormalsVertexIndexList() );
    }

    @Test
    public void testReadFaceWithWrongPrefix(){
        Face face = new Face();
        String[] array = new String[]{"ft", "1", "2", "3"};

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            face.ReadFromStringArray(array);
        });

        String expectedMessage = "Префикс должен быть 'f'";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testReadFaceWithWrongDataCount(){
        Face face = new Face();
        String[] array = new String[]{"ft", "1", "2"};

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            face.ReadFromStringArray(array);
        });

        String expectedMessage = "Длина входных данных должна быть не меньше 4";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testReadFaceWithNotNumberDataValue(){
        Face face = new Face();
        String[] array = new String[]{"f", "1", "2", "p"};

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            face.ReadFromStringArray(array);
        });

        String expectedMessage = "Не удается распарсить int";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }
}
