package von.rims;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementTest {

    @Test
    public void testConstructorAndGetterMethods(){
        int x = 10;
        int y = 20;
        Element element = new Element(x, y);

        assertEquals(x, element.getX(), "getX() should return the correct value");
        assertEquals(y, element.getY(), "getY() should return the correct value");
    }
}