import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest {

    @Test
    public void setupCheck() {
        System.out.println("TestNG setup working ✅");
        Assert.assertTrue(true);
    }
}
