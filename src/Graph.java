import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Graph {

    private HashMap<Node, ArrayList<Edge>> adjList = new HashMap<>();


    /*    public void readFiles(String file) {
            FileReader fileReader = new FileReader(file);
            BufferedReader bf = new BufferedReader(file);
        }*/
    public void printNodes() {
        for (int i = 0; i < nodeList.size(); i++) {
            System.out.println(nodeList.get(i).toString());
        }
    }

    public void printEdges() {
        for (int i = 0; i < edgeList.size(); i++) {
            System.out.println(edgeList.get(i).toString());
        }
    }

    public void readNodes() {
        try {
            FileReader fileReader = new FileReader("info/sl_stops.txt");
            Scanner sc = new Scanner(fileReader);
            String info = sc.nextLine();
            while (sc.hasNextLine()) {
                StringTokenizer stringTokenizer = new StringTokenizer(sc.nextLine(), ",");
                int stopId = Integer.parseInt(stringTokenizer.nextToken());
                String stopName = stringTokenizer.nextToken();
                double stopLat = Double.parseDouble(stringTokenizer.nextToken());
                double stopLong = Double.parseDouble(stringTokenizer.nextToken());

                nodeList.add(new Node(stopId, stopName, stopLat, stopLong));
            }
            fileReader.close();
            sc.close();
        } catch (IOException e) {
            System.out.println("Message: " + e.getMessage());
        }
    }

    public void readEdges() {
        try {
            FileReader fileReader = new FileReader("info/sl_stop_times.txt");
            Scanner sc = new Scanner(fileReader);
            String info = sc.nextLine();
            while (sc.hasNextLine()) {
                StringTokenizer stringTokenizer = new StringTokenizer(sc.nextLine(), ",");
                long tripId1 = Long.parseLong(stringTokenizer.nextToken());
                int arrivalTime1 = Integer.parseInt(stringTokenizer.nextToken());
                int departureTime1 = Integer.parseInt(stringTokenizer.nextToken());
                int stopId1 = Integer.parseInt(stringTokenizer.nextToken());

                Scanner nextScanner = sc;

                StringTokenizer stringTokenizer2 = new StringTokenizer(nextScanner.nextLine(), ",");

                long tripId2 = Long.parseLong(stringTokenizer.nextToken());
                int arrivalTime2 = Integer.parseInt(stringTokenizer.nextToken());
                int departureTime2 = Integer.parseInt(stringTokenizer.nextToken());
                int stopId = Integer.parseInt(stringTokenizer.nextToken());

            }
            fileReader.close();
            sc.close();
        } catch (IOException e) {
            System.out.println("Message: " + e.getMessage());
        }
    }

    public void readEdges2() {
        try {
            FileReader fileReader = new FileReader("info/sl_stop_times.txt");
            Scanner sc = new Scanner(fileReader);
            StringTokenizer st1;
            StringTokenizer st2;

            String info = sc.nextLine();
            while (sc.hasNextLine()) {
                st1 = new StringTokenizer(sc.nextLine(), ",");
                long tripId1 = Long.parseLong(st1.nextToken());
                String arrivalTime1 = st1.nextToken();
                String departureTime1 = st1.nextToken();
                int stopId1 = Integer.parseInt(st1.nextToken());

                st2 = new StringTokenizer(sc.nextLine(), ",");
                long tripId2 = Long.parseLong(st2.nextToken());
                String arrivalTime2 = st2.nextToken();
                String departureTime2 = st2.nextToken();
                int stopId2 = Integer.parseInt(st2.nextToken());

                if (tripId1 == tripId2) {
                    for (Node node1 : nodeList)
                        if (node1.getStopId() == stopId1)
                            for (Node node2 : nodeList)
                                if (node2.getStopId() == stopId2)
                                    edgeList.add(new Edge(node2, node1, arrivalTime2, departureTime1, tripId1));
                }
            }
            fileReader.close();
            sc.close();
        } catch (IOException e) {
            System.out.println("Message: " + e.getMessage());
        }
    }

/*    private void readStops(){
        FileReader fileReader = new FileReader(file);
        BufferedReader bf = new BufferedReader(file);
    }
    private void readTrips(){
        FileReader fileReader = new FileReader(file);
        BufferedReader bf = new BufferedReader(file);
    }*/
}
