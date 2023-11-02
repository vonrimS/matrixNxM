package von.rims;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    private static Matrix matrix;

    @BeforeAll
    public static void setup(){
        matrix = new Matrix(3, 3);
    }

    // Set access level as 'accessible' to private method with Reflection API
    private Method getAccessibleFindCornerNeighborsMethod(String methodName) throws NoSuchMethodException {
        Method method = matrix.getClass().getDeclaredMethod(methodName, int.class, int.class);
        method.setAccessible(true);
        return method;
    }


    @Test
    public void testMatrixInitialization() {
        int rows = 3;
        int columns = 3;

        assertEquals(rows, matrix.getRows(), "Matrix should have correct number of rows");
        assertEquals(columns, matrix.getColumns(), "Matrix should have correct number of columns");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                assertNotNull(matrix.getElement(i, j), "Matrix element should not be null");
                assertEquals(i, matrix.getElement(i, j).getX(), "Element x coordinate should be correct");
                assertEquals(j, matrix.getElement(i, j).getY(), "Element y coordinate should be correct");
            }
        }
    }

    @Test
    public void testMatrixBounds() {
        assertNull(matrix.getElement(3,3), "Getting element outside bounds should return null");
        assertNull(matrix.getElement(-1,-1), "Getting element with negative indices should return null");
    }

    @Test
    public void testMatrixElementsCorrectness() {
        int rows = 3;
        int columns = 3;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Element element = matrix.getElement(i, j);
                assertNotNull(element, "Element at (" + i + ", " + j + ") should not be null");
                assertEquals(i, element.getX(), "Element at (" + i + ", " + j + ") should have correct X coordinate");
                assertEquals(j, element.getY(), "Element at (" + i + ", " + j + ") should have correct Y coordinate");
            }
        }
    }

    @Test
    public void testFindNeighborsForCornerElement() {
        Element cornerElement = matrix.getElement(0, 0);
        Element[] neighbors = matrix.findNeighbors(cornerElement);

        assertNotNull(neighbors, "Neighbors array should not be null");
        assertEquals(3, neighbors.length, "Corner element should have 3 neighbors");
    }


    @Test
    public void testFindNeighborsForNonCornerElement() {
        Element nonCornerElement = matrix.getElement(1, 1);
        Element[] neighbors = matrix.findNeighbors(nonCornerElement);

        assertNotNull(neighbors, "Neighbors array should not be null");
        assertEquals(8, neighbors.length, "Non-corner element should have 8 neighbors");
    }

    @Test
    public void testFindNeighborsForNullElement() {
        assertThrows(IllegalArgumentException.class, () -> {
            matrix.findNeighbors(null);
        }, "Method should throw IllegalArgumentException for null element");
    }

    @Test
    public void testFindCornerNeighborsTopLeft() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getAccessibleFindCornerNeighborsMethod("findCornerNeighbors");

        Element[] neighbors = (Element[]) method.invoke(matrix, 0, 0);

        assertNotNull(neighbors, "Neighbors array should not be null");
        assertEquals(3, neighbors.length, "Top left corner element should have 3 neighbors");

        assertEquals(matrix.getElement(0, 1), neighbors[0], "Top neighbor should be correct");
        assertEquals(matrix.getElement(1, 0), neighbors[1], "Right neighbor should be correct");
        assertEquals(matrix.getElement(1, 1), neighbors[2], "Bottom-right neighbor should be correct");
    }




}