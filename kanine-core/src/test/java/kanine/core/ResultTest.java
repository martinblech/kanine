package kanine.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResultTest {

    @Test public void string() {
        assertEquals(new Result(1, 0.5f).toString(), "Result(1, 0.5)");
    }

}
