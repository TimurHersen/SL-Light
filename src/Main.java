public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.readNodes();
        //graph.printNodes();

        graph.readEdges();
        graph.printEdges();
    }
}
