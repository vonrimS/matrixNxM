package von.rims;


public class App {
    public static void main(String[] args) {
       Matrix matrix = new Matrix(10, 10);
       Element element = new Element(9, 9);

       Element[] neighbors = matrix.findNeighbors(element);

       for (Element neighbor : neighbors){
           System.out.println("Neighbor: (" + neighbor.getX() + ", " + neighbor.getY() + ")");
       }
    }
}
