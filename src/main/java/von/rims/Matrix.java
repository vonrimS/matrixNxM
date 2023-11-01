package von.rims;

public class Matrix {
    private int rows;
    private int columns;
    private Element[][] matrix;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new Element[rows][columns];
        // Заполняем матрицу элементами
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                matrix[i][j] = new Element(i, j);
            }
        }
    }

    public Element[] findNeighbors(Element element){
        int x = element.getX();
        int y = element.getY();
        // Проверяем соседние элементы
        Element[] neighbors = new Element[8];

        // Если элемент находится в углу матрицы
        if (x == 0 && y == 0) {
            // Левый верхний угол
            neighbors = new Element[3];
            neighbors[0] = matrix[x][y + 1];     // Верхний сосед
            neighbors[1] = matrix[x + 1][y];     // Правый сосед
            neighbors[2] = matrix[x + 1][y + 1]; // Правый нижний угол
        } else if (x == 0 && y == columns - 1) {
            // Правый верхний угол
            neighbors = new Element[3];
            neighbors[0] = matrix[x][y - 1];     // Левый сосед
            neighbors[1] = matrix[x + 1][y];     // Правый сосед
            neighbors[2] = matrix[x + 1][y - 1]; // Левый нижний угол
        } else if (x == rows - 1 && y == 0) {
            // Левый нижний угол
            neighbors = new Element[3];
            neighbors[0] = matrix[x][y + 1];     // Верхний сосед
            neighbors[1] = matrix[x - 1][y];     // Левый сосед
            neighbors[2] = matrix[x - 1][y + 1]; // Правый верхний угол
        } else if (x == rows - 1 && y == columns - 1) {
            // Правый нижний угол
            neighbors = new Element[3];
            neighbors[0] = matrix[x][y - 1];     // Левый сосед
            neighbors[1] = matrix[x - 1][y];     // Верхний сосед
            neighbors[2] = matrix[x - 1][y - 1]; // Левый верхний угол
        } else {
            // Если элемент не в углу, то есть 8 соседних элементов
            neighbors = new Element[8];
            neighbors[0] = matrix[x - 1][y - 1]; // Левый верхний угол
            neighbors[1] = matrix[x - 1][y];     // Верхний сосед
            neighbors[2] = matrix[x - 1][y + 1]; // Правый верхний угол
            neighbors[3] = matrix[x][y - 1];     // Левый сосед
            neighbors[4] = matrix[x][y + 1];     // Правый сосед
            neighbors[5] = matrix[x + 1][y - 1]; // Левый нижний угол
            neighbors[6] = matrix[x + 1][y];     // Нижний сосед
            neighbors[7] = matrix[x + 1][y + 1]; // Правый нижний угол
        }

        return neighbors;
    }


}
