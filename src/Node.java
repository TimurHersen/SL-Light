public class Node {

    private int stopId;
    private String stopName;
    private double latitude;
    private double longitude;

    public Node(int id, String name, double latitude, double longitude) {
        this.stopId = id;
        this.stopName = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getStopId() {
        return stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Node{" +
                "stopId=" + stopId +
                ", stopName='" + stopName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
