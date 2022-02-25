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



/*        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = "10:15:00";
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(graph.getClosestDepartures("Medborgarplatsen T-bana", date));*/

        System.out.println(graph.getDistanceBetween("Gröndal Spårv", "Kista T-bana") + " kilometers");

/*
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the current time?(format: 0000)");
        int time = scanner.nextInt();
        scanner.nextLine();

        System.out.println("What station is closest to you?");
        String startingPoint = scanner.nextLine();

        System.out.println("To which station would you like to go?");
        String destination = scanner.nextLine();


        System.out.println("current time: " + time);
        System.out.println("current station: " + startingPoint);
        System.out.println("destination station: " + destination);



*/

    }
}
