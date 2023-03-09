package test.com.example.obj;

import main.com.example.obj.types.Face;
import main.com.example.obj.types.TextureVertex;
import org.junit.Assert;
import org.junit.Test;

public class TestTextureVertex {
    @Test
    public void testReadTextureVertexWithTextureVertexData(){
        TextureVertex vt = new TextureVertex();
        String[] array = new String[]{"vt", "1", "0.5"};
        vt.ReadFromStringArray(array);

        Assert.assertEquals( 1.0f, vt.getX(), 0.0001 );
        Assert.assertEquals( 0.5f, vt.getY(), 0.0001 );
    }

    @Test
    public void testReadTextureVertexWithWrongPrefix(){
        TextureVertex vt = new TextureVertex();
        String[] array = new String[]{"vtt", "1", "0.5"};

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            vt.ReadFromStringArray(array);
        });

        String expectedMessage = "Префикс должен быть 'vt'";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testReadTextureVertexWithWrongDataCount(){
        TextureVertex vt = new TextureVertex();
        String[] array = new String[]{"vt", "1"};

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            vt.ReadFromStringArray(array);
        });

        String expectedMessage = "Длина входных данных должна быть не меньше 3";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testReadTextureVertexWithNotNumberDataValue(){
        TextureVertex vt = new TextureVertex();
        String[] array = new String[]{"vt", "1", "p"};

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            vt.ReadFromStringArray(array);
        });

        String expectedMessage = "Не удается прочитать параметр Y как double";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }
}
