public class Node {

    private int stopId;
    private String stopName;
    private double latitude;
    private double longitude;
    private double h;

    public Node(int id, String name, double latitude, double longitude) {
        this.stopId = id;
        this.stopName = name;
        this.latitude = latitude;
        this.longitude = longitude;


    }
   public int calculateH(Node destination){
        //TODO: Ha uträkning här för att returnera ett värde in i konstruktorn, som senare bli instansvariabel
        this.h = (latitude + longitude) - (destination.getLatitude() + destination.getLongitude());
        return 0;
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

/*    public int getF(){
        return h;
    }*/
    public void setF(int f){
        this.h = h;
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
