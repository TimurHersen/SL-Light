import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
    //TODO: Shortest path-algo

    private final double EARTH_RADIUS = 6372.8;
    private final int AVG_SPEED = 50;

    private HashMap<Node, ArrayList<Edge>> adjList = new HashMap<>();
    private HashMap<Long, String> routesIdsAndLines = new HashMap<>();
    private HashMap<Long, Long> tripsAndRoutes = new HashMap<>();

    private ArrayList<Node> nodeList = new ArrayList<>();
    private ArrayList<Edge> edgeList = new ArrayList<>();

    private Scanner scanner;
    private FileReader fileReader;

    private StringTokenizer tokenizer;
    private StringTokenizer tokenizer2;



    private int hValue(String current, String destination){
        return (int)((getDistanceBetween(current, destination) / AVG_SPEED) * 60);
    }

    private double getDistanceBetween(String firstStation, String secondStation) {
        Node first = null;
        Node second = null;
        for (Map.Entry<Node, ArrayList<Edge>> entry : adjList.entrySet()) {
            if (entry.getKey().getStopName().equalsIgnoreCase(firstStation))
                first = entry.getKey();
            if (entry.getKey().getStopName().equalsIgnoreCase(secondStation))
                second = entry.getKey();
        }
        if (first == null || second == null)
            throw new IllegalArgumentException("No such node exists");

        return haversineFormula(first.getLatitude(), first.getLongitude(), second.getLatitude(), second.getLongitude());
    }

    private double haversineFormula(double lat1, double long1, double lat2, double long2) {
        /**
         * @source: https://www.geeksforgeeks.org/haversine-formula-to-find-distance-between-two-points-on-a-sphere/
         */
        double diffLat = Math.toRadians(lat2 - lat1);
        double diffLong = Math.toRadians(long2 - long1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(diffLat / 2), 2) +
                Math.pow(Math.sin(diffLong / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);

        double c = 2 * Math.asin(Math.sqrt(a));
        return EARTH_RADIUS * c;
    }

    public List<Edge> getClosestDepartures(String station, Date time) {
        Node node = null;
        for (Map.Entry<Node, ArrayList<Edge>> entry : adjList.entrySet()) {
            if (entry.getKey().getStopName().equalsIgnoreCase(station))
                node = entry.getKey();
        }
        if (node == null)
            throw new IllegalArgumentException("No such station exists");

        ArrayList<Edge> edges = adjList.get(node);
        List<Edge> departures = new ArrayList<>();
        for (Edge e : edges) {
            if (e.getArrivalTime().after(time))
                departures.add(e);
        }

        Collections.sort(departures, new Comparator<Edge>() {
            @Override
            public int compare(Edge firstEdge, Edge secondEdge) {
                return firstEdge.getArrivalTime().compareTo(secondEdge.getArrivalTime());
            }
        });

        List<Edge> closestDepartures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            closestDepartures.add(departures.get(i));
        }

        return closestDepartures;
    }

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

    public void Astar(String start, String destination) {

        Stack<Node> openList = new Stack<>();
        ArrayList<Node> closedList = new ArrayList<>();
        Node destinationNode;

        for (Map.Entry<Node, ArrayList<Edge>> entry : adjList.entrySet()) {
            if (entry.getKey().getStopName().equals(start)) {

                Node startingNode = entry.getKey();
                startingNode.setF(0);
                openList.add(startingNode);
                System.out.println("StartingNode found");
            }
            if (entry.getKey().getStopName().equals(destination)) {
                destinationNode = entry.getKey();
                System.out.println("DestinationNode found");
            }

        }
        while (!openList.isEmpty()) {
            Node stackNode = openList.pop();

            ArrayList<Edge> routes = adjList.get(stackNode);


        }
        //TODO:


    }

}
