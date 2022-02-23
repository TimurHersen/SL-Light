import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Edge {

    private Node to;
    private String arrivalTime;
    private String departureFromPrevious;
    private String lineNr;
    private long tripId;
    private long weight;

    public Edge(Node to, String arrivalTime, String departureFromPrevious, long routeId, String lineNr) {
        this.to = to;
        this.arrivalTime = arrivalTime;
        this.departureFromPrevious = departureFromPrevious;
        this.tripId = routeId;
        this.lineNr = lineNr;
        this.weight = calculateWeight(arrivalTime, departureFromPrevious);
    }

    private long calculateWeight(String arrivalTime, String departureFromPrevious) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        long diffMinutes = 0;
        try {
            Date d1 = format.parse(arrivalTime);
            Date d2 = format.parse(departureFromPrevious);
            long diff = d1.getTime() - d2.getTime();
            diffMinutes = diff / (60 * 1000) % 60;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (diffMinutes < 0)
            throw new IllegalStateException("Weight can't be negative!");

        return diffMinutes;
    }

    public long getWeight() {
        return weight;
    }

    public long getTripId() {
        return tripId;
    }

    public Node getTo() {
        return to;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureFromPrevious() {
        return departureFromPrevious;
    }

    public String getLineNr(){ return lineNr; };

    @Override
    public String toString() {
        return "Edge{" +
                "to=" + to +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", departureFromPrevious='" + departureFromPrevious + '\'' +
                ", lineNr='" + lineNr + '\'' +
                ", tripId=" + tripId +
                ", weight=" + weight +
                '}' + "\n";
    }
}
