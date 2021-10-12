import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass newMainClass = new MainClass();

    @Test
    public void testGetLocalNumber(){
        int expected = 14;
        int actual = newMainClass.getLocalNumber();

        Assert.assertEquals("Class getLocalNumber does not return 14", expected, actual);
    }
}