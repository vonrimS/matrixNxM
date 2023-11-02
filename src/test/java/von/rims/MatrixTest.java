package von.rims;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    public void testMatrixInitialization() {
        int rows = 3;
        int columns = 3;
        Matrix matrix = new Matrix(rows, columns);

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
        Matrix matrix = new Matrix(3, 3);
        assertNull(matrix.getElement(3,3), "Getting element outside bounds should return null");
        assertNull(matrix.getElement(-1,-1), "Getting element with negative indices should return null");
    }

    @Test
    public void testMatrixElementsCorrectness() {
        int rows = 3;
        int columns = 3;
        Matrix matrix = new Matrix(rows, columns);

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
        Matrix matrix = new Matrix(3, 3);
        Element cornerElement = matrix.getElement(0, 0);
        Element[] neighbors = matrix.findNeighbors(cornerElement);

        assertNotNull(neighbors, "Neighbors array should not be null");
        assertEquals(3, neighbors.length, "Corner element should have 3 neighbors");
    }


    @Test
    public void testFindNeighborsForNonCornerElement() {
        Matrix matrix = new Matrix(3, 3);
        Element nonCornerElement = matrix.getElement(1, 1);
        Element[] neighbors = matrix.findNeighbors(nonCornerElement);

        assertNotNull(neighbors, "Neighbors array should not be null");
        assertEquals(8, neighbors.length, "Non-corner element should have 8 neighbors");
    }

    @Test
    public void testFindNeighborsForNullElement() {
        Matrix matrix = new Matrix(3, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            matrix.findNeighbors(null);
        }, "Method should throw IllegalArgumentException for null element");
    }

}