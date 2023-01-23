import com.example.Main;
import com.example.exception.IncorrectAvailableException;
import com.example.exception.IncorrectTimeException;
import org.junit.Test;

import static org.junit.Assert.fail;

public class MainTest {

    @Test
    public void testMainWithValidArguments() throws IncorrectTimeException, IncorrectAvailableException {
        String[] args = {"-u", "99.9", "-t", "45", "-f", "access.log"};
        Main.main(args);
        // assert that the Main.main() method executes without throwing any exceptions
    }

    @Test
    public void testMainWithMissingArgument() {
        String[] args = {"-u", "99.9"};
        try {
            Main.main(args);
            fail("Expected IncorrectTimeException to be thrown");
        } catch (IncorrectTimeException e) {
            // assert that the exception message is as expected
        } catch (IncorrectAvailableException e) {
            fail("Expected IncorrectTimeException to be thrown");
        }
    }

    @Test
    public void testMainWithBigFile() throws IncorrectTimeException, IncorrectAvailableException {
        String[] args = {"-u", "99.9", "-t", "45", "-f", "access_big.log"};
        Main.main(args);
        // assert that the Main.main() method executes without throwing any exceptions
    }
}