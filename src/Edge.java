public class Edge {

    private Node to;
    private int arrivalTime;
    private int departureTime;
    private int stopId;
    private long routeId;

    public Edge(Node to, int arrivalTime, int departureTime, int stopId, long routeId) {
        this.to = to;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stopId = stopId;
        this.routeId = routeId;
    }

    public long getRouteId() {
        return routeId;
    }

    public Node getTo() {
        return to;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public int getStopId() {
        return stopId;
    }
}
