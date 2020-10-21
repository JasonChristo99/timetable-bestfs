import com.company.model.DataLayer;
import com.company.util.InputReader;
import com.company.util.SimplePrinter;
import org.junit.Test;

public class DataLayerTest {
    @Test
    public void dataInitTest() {
        DataLayer dataLayer = new DataLayer();
        // read input
        dataLayer.setRequestList(InputReader.readRequestsAndConstantsFromFile());
        dataLayer.initTeachers();
        SimplePrinter.printList(dataLayer.getRequestList());
        SimplePrinter.printList(dataLayer.getTeachersOrdered());
        SimplePrinter.printHashMap(dataLayer.getTeacherRequestIndex());

    }
}
