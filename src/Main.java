import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.readTrips();
        graph.readRouteNames();

        graph.readNodes();

        graph.readEdges();





    }
}
