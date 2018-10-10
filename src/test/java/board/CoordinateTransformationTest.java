package board;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CoordinateTransformationTest {

    @Test
    public void testApply() {
        CoordinateTransformation identity = new CoordinateTransformation(0, 0);
        assertThat(identity.apply(new Coordinate(0,0)), is(new Coordinate(0,0)));
        assertThat(identity.apply(new Coordinate(3,3)), is(new Coordinate(3,3)));
        assertThat(identity.apply(new Coordinate(-1,-1)), is(new Coordinate(-1,-1)));


        CoordinateTransformation positive = new CoordinateTransformation(1, 2);
        assertThat(positive.apply(new Coordinate(0, 0)), is(new Coordinate(1, 2)));
        assertThat(positive.apply(new Coordinate(-1, -2)), is(new Coordinate(0, 0)));

        CoordinateTransformation negative = new CoordinateTransformation(-1, -2);
        assertThat(negative.apply(new Coordinate(0, 0)), is(new Coordinate(-1, -2)));
        assertThat(negative.apply(new Coordinate(1, 2)), is(new Coordinate(0, 0)));
    }

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(CoordinateTransformation.class).verify();
    }
}