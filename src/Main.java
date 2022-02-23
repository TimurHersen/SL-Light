public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.readTrips();
        graph.readRouteNames();

        graph.readNodes();

        graph.readEdges();

        graph.printNodesAndEdges();

    }
}
