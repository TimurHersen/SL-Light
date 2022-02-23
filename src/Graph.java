import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
    //TEST KOMMENTAR FÖR ATT KONTROLLERA

    //TODO: Läs in sl_routes.txt för att koppla ihopa edges till vilken buss/tunnebana det är, krav enligt instruktioner.
    //TODO: Shortest path-algo

    private HashMap<Node, ArrayList<Edge>> adjList = new HashMap<>();
    private HashMap<Long, String> routesIdsAndLines = new HashMap<>();
    private HashMap<Long, Long> tripsAndRoutes = new HashMap<>();

    private ArrayList<Node> nodeList = new ArrayList<>();
    private ArrayList<Edge> edgeList = new ArrayList<>();

    private Scanner scanner;
    private FileReader fileReader;

    private StringTokenizer tokenizer;
    private StringTokenizer tokenizer2;


    public void printNodesAndEdges() {
        for (Map.Entry<Node, ArrayList<Edge>> entry : adjList.entrySet()) {
            System.out.println("*******STATION*******");
            System.out.println(entry.getKey() + "\n");
            System.out.println();
            System.out.println("*******DEPARTURES*******");
            System.out.println(entry.getValue().toString());
            System.out.println();
        }
    }

    public void readNodes() {
        try {
            fileReader = new FileReader("info/sl_stops.txt");
            scanner = new Scanner(fileReader);
            String info = scanner.nextLine();
            while (scanner.hasNextLine()) {
                tokenizer = new StringTokenizer(scanner.nextLine(), ",");
                int stopId = Integer.parseInt(tokenizer.nextToken());
                String stopName = tokenizer.nextToken();
                double stopLat = Double.parseDouble(tokenizer.nextToken());
                double stopLong = Double.parseDouble(tokenizer.nextToken());

                adjList.put(new Node(stopId, stopName, stopLat, stopLong), new ArrayList<>());
            }
            fileReader.close();
            scanner.close();
        } catch (IOException e) {
            System.out.println("Message: " + e.getMessage());
        }
    }

    public void readEdges() {
        try {
            fileReader = new FileReader("info/sl_stop_times.txt");
            scanner = new Scanner(fileReader);

            String info = scanner.nextLine();
            while (scanner.hasNextLine()) {
                tokenizer = new StringTokenizer(scanner.nextLine(), ",");
                long tripId1 = Long.parseLong(tokenizer.nextToken());
                String arrivalTime1 = tokenizer.nextToken();
                String departureTime1 = tokenizer.nextToken();
                int stopId1 = Integer.parseInt(tokenizer.nextToken());

                tokenizer2 = new StringTokenizer(scanner.nextLine(), ",");
                long tripId2 = Long.parseLong(tokenizer2.nextToken());
                String arrivalTime2 = tokenizer2.nextToken();
                String departureTime2 = tokenizer2.nextToken();
                int stopId2 = Integer.parseInt(tokenizer2.nextToken());

                if (tripId1 == tripId2)
                    createEdge(stopId1, stopId2, arrivalTime2, departureTime1, tripId1);
            }
            fileReader.close();
            scanner.close();
        } catch (IOException e) {
            System.out.println("Message: " + e.getMessage());
        }
    }

    private void createEdge(int stopId1, int stopId2, String arrivalTime, String departureTime, long tripId) {
        for (Node from : adjList.keySet())
            if (from.getStopId() == stopId1)
                for (Node to : adjList.keySet())
                    if (to.getStopId() == stopId2) {
                        long route = tripsAndRoutes.get(tripId);
                        String line = routesIdsAndLines.get(route);
                        adjList.get(from).add(new Edge(to, arrivalTime, departureTime, tripId, line));
                    }
    }

    public void readRouteNames() {
        try {
            fileReader = new FileReader("info/sl_routes.txt");
            scanner = new Scanner(fileReader);
            String info = scanner.nextLine();
            while (scanner.hasNextLine()) {
                tokenizer = new StringTokenizer(scanner.nextLine(), ",");
                long routeId = Long.parseLong(tokenizer.nextToken());
                tokenizer.nextToken();
                String route = tokenizer.nextToken();
                //NYTT
                routesIdsAndLines.put(routeId, route);

            }
            fileReader.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readTrips() {
        try {
            fileReader = new FileReader("info/sl_trips.txt");
            scanner = new Scanner(fileReader);
            String info = scanner.nextLine();
            while (scanner.hasNextLine()) {
                tokenizer = new StringTokenizer(scanner.nextLine(), ",");
                long routeId = Long.parseLong(tokenizer.nextToken());
                tokenizer.nextToken();
                long tripId = Long.parseLong(tokenizer.nextToken());
                tripsAndRoutes.put(tripId, routeId);
            }
            fileReader.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
