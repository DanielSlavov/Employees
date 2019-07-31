import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {


    public static int intervalIntersection(Date aStart, Date aEnd, Date bStart, Date bEnd) {
        Date lastStart;
        Date firstEnd;

        if (aStart.compareTo(bStart) > 0) {
            lastStart = aStart;
        } else {
            lastStart = bStart;
        }

        if (aEnd.compareTo(bEnd) < 0) {
            firstEnd = aEnd;
        } else {
            firstEnd = bEnd;
        }

        return (int) ((firstEnd.getTime() - lastStart.getTime()) / (1000 * 60 * 60 * 24));//return length in days
    }


    public static void main(String[] args) throws Exception {
        File inputFile = new File("input.txt");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(inputFile));

        List<Record> records = new ArrayList<>();
        Map<Pair, Integer> pairs = new HashMap<>();


        String line;
        while ((line = fileReader.readLine()) != null) {//Parses each row of the inputFile, normalizes the row into variables
            String[] tokens = line.split(", ");// and creates a Record object which is put into projectRecords HashMap
            int employeeId = Integer.parseInt(tokens[0]);
            int projectId = Integer.parseInt(tokens[1]);
            Date startDate;
            Date endDate;

            if (tokens[2].equals("NULL")) {
                startDate = new Date();
            } else {
                startDate = dateFormat.parse(tokens[2]);
            }

            if (tokens[3].equals("NULL")) {
                endDate = new Date();
            } else {
                endDate = dateFormat.parse(tokens[3]);
            }

            records.add(new Record(employeeId, projectId, startDate, endDate));
        }

        records.forEach(first -> {
            records.forEach(second -> {
                if (first.workedTogether(second)) {
                    Pair currentPair = new Pair(first, second);
                    if (pairs.get(currentPair) == null) {
                        pairs.put(currentPair, intervalIntersection(first.startDate, first.endDate, second.startDate, second.endDate));
                    } else {
                        pairs.put(currentPair, pairs.get(currentPair) + intervalIntersection(first.startDate, first.endDate, second.startDate, second.endDate));
                    }


                }
            });
        });

        Map.Entry<Pair, Integer> maxEntry = null;
        for (Map.Entry<Pair, Integer> entry : pairs.entrySet()) {
            if (maxEntry == null || maxEntry.getValue() < entry.getValue()) {
                maxEntry = entry;
            }

        }
        if (maxEntry == null) {
            System.out.println("no employees have worked together");
        } else {
            System.out.println(String.format("Employees %s and %s have worked together for %d days", maxEntry.getKey().getFirst(), maxEntry.getKey().getSecond(), maxEntry.getValue() / 2));
        }

    }
}
