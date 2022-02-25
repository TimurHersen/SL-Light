import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Edge {

    private Node to;
    private Date arrivalTime;
    private Date departureFromPrevious;
    private String lineNr;
    private long tripId;
    private long weight;

    public Edge(Node to, String arrivalTime, String departureFromPrevious, long routeId, String lineNr) {
        this.to = to;
        this.arrivalTime = getDateFormat(arrivalTime);
        this.departureFromPrevious = getDateFormat(departureFromPrevious);
        this.tripId = routeId;
        this.lineNr = lineNr;
        this.weight = calculateWeight(this.arrivalTime, this.departureFromPrevious);
    }

    private Date getDateFormat(String time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private long calculateWeight(Date arrivalTime, Date departureFromPrevious) {

        long diffMinutes = 0;
        long diff = arrivalTime.getTime() - departureFromPrevious.getTime();
        diffMinutes = diff / (60 * 1000) % 60;

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

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Date getDepartureFromPrevious() {
        return departureFromPrevious;
    }

    public String getLineNr() {
        return lineNr;
    }


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

/*    public class TimeComparator implements Comparator<Date>{

        @Override
        public int compare(Edge firstEdge, Edge secondEdge) {
            return Integer.compare(firstEdge.getArrivalTime(), secondEdge.getArrivalTime());
        }
    }*/
}



