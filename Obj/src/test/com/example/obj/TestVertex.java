package test.com.example.obj;

import main.com.example.obj.types.Vertex;
import org.junit.Assert;
import org.junit.Test;

public class TestVertex {
    @Test
    public void testReadVertexWithVertexData(){
        Vertex v = new Vertex();
        String[] array = new String[]{"v", "1", "0.5", "0.3"};
        v.ReadFromStringArray(array);

        Assert.assertEquals( 1.0f, v.getX(), 0.0001 );
        Assert.assertEquals( 0.5f, v.getY(), 0.0001 );
        Assert.assertEquals( 0.3f, v.getZ(), 0.0001 );
    }

    @Test
    public void testReadVertexWithWrongPrefix(){
        Vertex v = new Vertex();
        String[] array = new String[]{"vt", "1", "0.5", "0.3"};

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            v.ReadFromStringArray(array);
        });

        String expectedMessage = "Префикс должен быть 'v'";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testReadVertexWithWrongDataCount(){
        Vertex v = new Vertex();
        String[] array = new String[]{"v", "1"};

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            v.ReadFromStringArray(array);
        });

        String expectedMessage = "Длина входных данных должна быть не меньше 3";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testReadVertexWithNotNumberDataValue(){
        Vertex v = new Vertex();
        String[] array = new String[]{"v", "1", "p", "0.3"};

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            v.ReadFromStringArray(array);
        });

        String expectedMessage = "Не удается прочитать параметр Y как double";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }
}
