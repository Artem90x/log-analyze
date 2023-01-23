import com.example.analysis.LogLine;
import com.example.exception.LogException;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogLineTest {

    @Test
    public void testIsFailedWithSuccess() {
        LogLine logLineSuccess = new LogLine("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6db8fda9 HTTP/1.1\" 200 2 13.415114 \"-\" \"@list-item-updater\" prio:0");
        assertFalse(logLineSuccess.isFailed(45D));
    }

    @Test
    public void testIsFailedWithFailure() {
        LogLine logLineFailure = new LogLine("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6db8fda9 HTTP/1.1\" 501 2 15.415114 \"-\" \"@list-item-updater\" prio:0");
        assertTrue(logLineFailure.isFailed(45D));
    }

    @Test
    public void testGetStartTime() throws LogException {
        LogLine logLine = new LogLine("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6db8fda9 HTTP/1.1\" 200 2 64.415114 \"-\" \"@list-item-updater\" prio:0");
        assertEquals(logLine.getStartTime(), "16:47:03");
    }

    @Test
    public void testGetEndTime() throws LogException {
        LogLine logLine = new LogLine("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6db8fda9 HTTP/1.1\" 200 2 64.415114 \"-\" \"@list-item-updater\" prio:0");
        assertEquals(logLine.getEndTime(), "16:47:03");

        logLine = new LogLine("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6db8fda9 HTTP/1.1\" 200 2 64.415114 \"-\" \"@list-item-updater\" prio:0");
        assertEquals(logLine.getEndTime(), "16:47:03");
    }
}
