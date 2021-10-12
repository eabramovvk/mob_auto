import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass newMainClass = new MainClass();

    @Test
    public void testGetClassString()
    {
        boolean helloWithUpperH = newMainClass.getClassString().contains("Hello");
        boolean helloWithLowerH = newMainClass.getClassString().contains("hello");
        Assert.assertTrue("There is no Hello or hello substring in getClassString invoke result", helloWithUpperH || helloWithLowerH);
    }
}