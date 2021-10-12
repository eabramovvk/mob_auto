import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass newMainClass = new MainClass();

    @Test
    public void testGetClassNumber()
    {
        int actual = newMainClass.getClassNumber();
        int expected = 45;
        Assert.assertTrue("getClassNumber method invoke result is equal or lower than " + expected, actual > expected);
    }
}