package von.rims;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    private static Matrix matrix;
    private Method method = getAccessibleFindCornerNeighborsMethod("findCornerNeighbors");

    MatrixTest() throws NoSuchMethodException {
    }

    @BeforeAll
    public static void setup(){
        matrix = new Matrix(3, 3);
    }

    // Set access level as 'accessible' to private method 'findCornerNeighbors' with Reflection API
    private Method getAccessibleFindCornerNeighborsMethod(String methodName) throws NoSuchMethodException {
        Method method = matrix.getClass().getDeclaredMethod(methodName, int.class, int.class);
        method.setAccessible(true);
        return method;
    }

    private boolean invokeIsCornerPosition(int x, int y) throws Exception {
        Method method = matrix.getClass().getDeclaredMethod("isCornerPosition", int.class, int.class);
        method.setAccessible(true);
        return (boolean) method.invoke(matrix, x, y);
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
    public void testFindCornerNeighborsTopLeft() throws InvocationTargetException, IllegalAccessException {
        Element[] neighbors = (Element[]) method.invoke(matrix, 0, 0);

        assertNotNull(neighbors, "Neighbors array should not be null");
        assertEquals(3, neighbors.length, "Top left corner element should have 3 neighbors");

        assertEquals(matrix.getElement(0, 1), neighbors[0], "Top neighbor should be correct");
        assertEquals(matrix.getElement(1, 0), neighbors[1], "Right neighbor should be correct");
        assertEquals(matrix.getElement(1, 1), neighbors[2], "Bottom-right neighbor should be correct");
    }

    @Test
    public void testFindCornerNeighborsTopRight() throws  InvocationTargetException, IllegalAccessException {
        Element[] neighbors = (Element[]) method.invoke(matrix, 0, 2);

        assertNotNull(neighbors, "Neighbors array should not be null");
        assertEquals(3, neighbors.length, "Top right corner element should have 3 neighbors");

        assertEquals(matrix.getElement(0, 1), neighbors[0], "Left neighbor should be correct");
        assertEquals(matrix.getElement(1, 2), neighbors[1], "Bottom neighbor should be correct");
        assertEquals(matrix.getElement(1, 1), neighbors[2], "Bottom-left neighbor should be correct");
    }

    @Test
    public void testFindCornerNeighborsBottomLeft() throws  InvocationTargetException, IllegalAccessException {
        Element[] neighbors = (Element[]) method.invoke(matrix, 2, 0);

        assertNotNull(neighbors, "Neighbors array should not be null");
        assertEquals(3, neighbors.length, "Bottom left corner element should have 3 neighbors");

        assertEquals(matrix.getElement(2, 1), neighbors[0], "Top neighbor should be correct");
        assertEquals(matrix.getElement(1, 0), neighbors[1], "Left neighbor should be correct");
        assertEquals(matrix.getElement(1, 1), neighbors[2], "Top-right neighbor should be correct");
    }

    @Test
    public void testFindCornerNeighborsBottomRight() throws InvocationTargetException, IllegalAccessException {
        Element[] neighbors = (Element[]) method.invoke(matrix, 2, 2);

        assertNotNull(neighbors, "Neighbors array should not be null");
        assertEquals(3, neighbors.length, "Bottom right corner element should have 3 neighbors");

        assertEquals(matrix.getElement(2, 1), neighbors[0], "Left neighbor should be correct");
        assertEquals(matrix.getElement(1, 2), neighbors[1], "Top neighbor should be correct");
        assertEquals(matrix.getElement(1, 1), neighbors[2], "Top-left neighbor should be correct");
    }

    @Test
    public void testIsCornerPositionTopLeft() throws Exception {
        assertTrue(invokeIsCornerPosition(0, 0), "Top-left corner should be identified as corner");
    }

    @Test
    public void testIsCornerPositionTopRight() throws Exception {
        assertTrue(invokeIsCornerPosition(0, matrix.getColumns() - 1), "Top-right corner should be identified as corner");
    }

    @Test
    public void testIsCornerPositionBottomLeft() throws Exception {
        assertTrue(invokeIsCornerPosition(matrix.getRows() - 1, 0), "Bottom-left corner should be identified as corner");
    }

    @Test
    public void testIsCornerPositionBottomRight() throws Exception {
        assertTrue(invokeIsCornerPosition(matrix.getRows() - 1, matrix.getColumns() - 1), "Bottom-right corner should be identified as corner");
    }

    @Test
    public void testIsCornerPositionNotCorner() throws Exception {
        assertFalse(invokeIsCornerPosition(1, 1), "Non-corner position should not be identified as corner");
    }
}