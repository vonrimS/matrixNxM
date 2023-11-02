package von.rims;

public class Matrix {
    private int rows;
    private int columns;
    private Element[][] matrix;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Element getElement(int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            return matrix[row][column];
        } else {
            // In case of the index is out of the bound
            return null;
        }
    }

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new Element[rows][columns];
        // Populate the matrix with Elements
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = new Element(i, j);
            }
        }
    }


    public Element[] findNeighbors(Element element) {
        if (element == null)
            throw new IllegalArgumentException("Element cannot be null");

        int x = element.getX();
        int y = element.getY();

        if (isCornerPosition(x, y)) {
            // Handle corner positions
            return findCornerNeighbors(x, y);
        } else {
            return findNonCornerNeighbors(x, y);
        }
    }

    private Element[] findCornerNeighbors(int x, int y) {
        Element[] neighbors = new Element[3];

        if (x == 0 && y == 0) {
            // Left top corner
            neighbors[0] = matrix[x][y + 1];     // Top neighbor
            neighbors[1] = matrix[x + 1][y];     // Right neighbor
            neighbors[2] = matrix[x + 1][y + 1]; // Bottom-right neighbor
        } else if (x == 0 && y == columns - 1) {
            // Right top corner
            neighbors[0] = matrix[x][y - 1];     // Left neighbor
            neighbors[1] = matrix[x + 1][y];     // Right neighbor
            neighbors[2] = matrix[x + 1][y - 1]; // Bottom-left neighbor
        } else if (x == rows - 1 && y == 0) {
            // Left bottom corner
            neighbors[0] = matrix[x][y + 1];     // Top neighbor
            neighbors[1] = matrix[x - 1][y];     // Left neighbor
            neighbors[2] = matrix[x - 1][y + 1]; // Top-right neighbor
        } else if (x == rows - 1 && y == columns - 1) {
            // Right bottom corner
            neighbors[0] = matrix[x][y - 1];     // Left neighbor
            neighbors[1] = matrix[x - 1][y];     // Top neighbor
            neighbors[2] = matrix[x - 1][y - 1]; // Top-left neighbor
        }

        return neighbors;
    }

    private Element[] findNonCornerNeighbors(int x, int y) {
        // Handle non-corner positions
        Element[] neighbors = new Element[8];
        neighbors[0] = matrix[x - 1][y - 1]; // Top-left neighbor
        neighbors[1] = matrix[x - 1][y];     // Top neighbor
        neighbors[2] = matrix[x - 1][y + 1]; // Top-right neighbor
        neighbors[3] = matrix[x][y - 1];     // Left neighbor
        neighbors[4] = matrix[x][y + 1];     // Right neighbor
        neighbors[5] = matrix[x + 1][y - 1]; // Bottom-left neighbor
        neighbors[6] = matrix[x + 1][y];     // Bottom neighbor
        neighbors[7] = matrix[x + 1][y + 1]; // Bottom-right neighbor
        return neighbors;
    }


    private boolean isCornerPosition(int x, int y) {
        return (x == 0 && y == 0) ||
                (x == 0 && y == columns - 1) ||
                (x == rows - 1 && y == 0) ||
                (x == rows - 1 && y == columns - 1);
    }


}
