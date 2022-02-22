public class Edge {
    //TODO: implementera en calculateWeight metod som tar emot arrival- och departureTime som omvandlas
    // till heltal och räknar ut mellanskillanden

    private Node to;
    private Node from;
    private String arrivalTime; //Ändrat till string eftersom tokenizern inte kan omvandla formaten 08:33:00 till en int!
    private String departureTime;
    private int stopId;
    private long routeId;

/*    public Edge(Node to, int arrivalTime, int departureTime, int stopId, long routeId) {
        this.to = to;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stopId = stopId;
        this.routeId = routeId;
    }*/

    public Edge(Node to, Node from, String  arrivalTime, String departureTime, long routeId) {
        this.to = to;
        this.from = from;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.routeId = routeId;
    }

    public long getRouteId() {
        return routeId;
    }

    public Node getTo() {
        return to;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public int getStopId() {
        return stopId;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "to=" + to +
                ", from=" + from +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", stopId=" + stopId +
                ", routeId=" + routeId +
                '}';
    }
}
