import com.company.util.InputReader;
import com.company.model.Request;
import org.junit.Test;

import java.util.List;

public class InputReaderTest {
    @Test
    public void readRequestsAndConstantsFromFileTest() {
        List<Request> requests = InputReader.readRequestsAndConstantsFromFile();
        for (Request request : requests)
            System.out.println(request);
    }
}
