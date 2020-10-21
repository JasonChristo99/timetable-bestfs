import com.company.util.Constants;
import com.company.model.DataLayer;
import com.company.util.InputReader;
import com.company.model.State;
import org.junit.Before;
import org.junit.Test;

public class StateTest {
    DataLayer dataLayer;
    State state;

    @Before
    public void setUp() {
        dataLayer = new DataLayer();
        // read input
        dataLayer.setRequestList(InputReader.readRequestsAndConstantsFromFile());
        dataLayer.initTeachers();
        // init state
        state = new State(dataLayer.getTeachersOrdered().size(), Constants.SCH_DAYS * Constants.SCH_HRS_PDAY);
//        initialState.print();
        state.fillWithRequests(dataLayer);
    }

    @Test
    public void testFill() {
        state.print();
        state.swapInRowEmptyWithNonEmpty(0, 6, 1);
        state.print();
        state.swapInRowEmptyWithNonEmpty(0, 4, 0);
        state.print();
        new State(state).print();
    }
}
