package von.rims;


public class App {
    public static void main(String[] args) {
       Matrix matrix = new Matrix(10, 10);
       Element element = new Element(0, 0);

       Element[] neighbors = matrix.findNeighbors(element);

       for (Element neighbor : neighbors){
           System.out.println("Neighbor: (" + neighbor.getX() + ", " + neighbor.getY() + ")");
       }
    }
}
