import static org.junit.Assert.assertEquals;

import com.example.exception.IncorrectAvailableException;
import com.example.exception.IncorrectTimeException;
import com.example.service.ArgsService;
import org.junit.Test;

public class ArgsServiceTest {

    @Test
    public void testVerifyStart_validArgs() throws IncorrectTimeException, IncorrectAvailableException {
        String[] args = {"-u", "0.5", "-t", "10", "-f", "test.log"};
        ArgsService service = new ArgsService(args);
        service.verifyStart();
        assertEquals(0.5, service.getMinAvailablePercentage(), 0);
        assertEquals(10, service.getMinTimeInMc(), 0);
        assertEquals("test.log", service.getFileName());
    }
}
