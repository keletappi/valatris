package board;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Coordinate.class).verify();
    }
}