import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
public class TraceRoute {

    public static void main(String[] args) throws IOException {
        try {
            File file = new File("C:\\Users\\admin\\Downloads\\log_sample.txt");
            Scanner scan = new Scanner(file);

            String fileContent = "";
            while (scan.hasNextLine()) {
                fileContent = fileContent.concat(scan.nextLine() + "\n");
                //System.out.println(scan.nextLine());
            }

            String[] fileContentArray = getTraceRouteFileArray(fileContent);
            //System.out.println(Arrays.toString(fileContentArray));
            double totalNumberOfReachableRoutes = 0;
            double totalNumberOfHopsOnReachableRoutes = 0;
            double totalNumberOfHopsOnTheLastLineOfReachableRoute = 0;
            double sumOfAverageTimeTakenOnTheLastLineOfReachableRoute = 0;

            for (int i = 0; i < fileContentArray.length; i++) {
                //if(i==0) {
                // System.out.println(fileContentArray[i]);
                //}
                //System.out.println(checkIfReachable(fileContentArray[i]));
                if (checkIfReachable(fileContentArray[i])) {
                    totalNumberOfReachableRoutes++;
                    totalNumberOfHopsOnReachableRoutes = totalNumberOfHopsOnReachableRoutes + getNumberOfHops(fileContentArray[i]);
                    //System.out.println(getNumberOfHops(fileContentArray[i]));
                    //if(totalNumberOfHopsOnTheLastLineOfTraceRoute(fileContentArray[i])==3){
                    sumOfAverageTimeTakenOnTheLastLineOfReachableRoute = sumOfAverageTimeTakenOnTheLastLineOfReachableRoute + averageDelay(fileContentArray[i]);
                    totalNumberOfHopsOnTheLastLineOfReachableRoute = totalNumberOfHopsOnTheLastLineOfReachableRoute + totalNumberOfHopsOnTheLastLineOfTraceRoute(fileContentArray[i]);
                    //}

                    System.out.println("-----------------------------------------------");
                }
            }
            
            System.out.println("**************************************************************");
            System.out.println("FINAL TALLY");
            double averageNumberOfHops = totalNumberOfHopsOnReachableRoutes / totalNumberOfReachableRoutes;
            System.out.println("Average Number of hops is " + averageNumberOfHops);
            
            
            double averageDelayForAllRoutes = sumOfAverageTimeTakenOnTheLastLineOfReachableRoute / totalNumberOfReachableRoutes;
            
            System.out.println("Count of all hops taken in the last line " + totalNumberOfHopsOnTheLastLineOfReachableRoute);
            System.out.println("Sum of time taken for hops is " + sumOfAverageTimeTakenOnTheLastLineOfReachableRoute );
           
            
            //System.out.println(sumOfAverageDelaysForAllRoutes);
            System.out.println("Average Delay is " + sumOfAverageTimeTakenOnTheLastLineOfReachableRoute + "/" + totalNumberOfHopsOnTheLastLineOfReachableRoute + " which equals to " +   averageDelayForAllRoutes);
            //boolean checkIfReachable = checkIfReachable("192.168.1.1");

        } catch (FileNotFoundException e) {
            System.out.println("The file you entered was not found");
        }
        // TODO Auto-generated method stub

    }


    public static String[] getTraceRouteFileArray(String traceRouteString) {
        return traceRouteString.split("traceroute");
    }



    public static boolean checkIfReachable(String singleTraceRoute) {
        //Lets get the IP addresss which should be inclosed in a bracket:

        String singleTraceRouteArray[] = singleTraceRoute.split("\\r?\\n");




        String ipAddressAtTheBeginningOfTheTraceRoute = getIPAddressFromString(singleTraceRouteArray[0]);
        String ipAddressAtTheEndOfTheTraceRoute = getIPAddressFromString(singleTraceRouteArray[singleTraceRouteArray.length - 1]);

        //We cannot do this because the last line can contain multiple IP address and the getIPAddressFunction only retruns the first IP address it finds
        //If both IPs are equal and it does not retrun not found. No need to check if the ip at the end of the trace is not found because the first condition will handle it
        //if(ipAddressAtTheBeginningOfTheTraceRoute.equals(ipAddressAtTheEndOfTheTraceRoute) && ipAddressAtTheBeginningOfTheTraceRoute!= "IP NOT FOUND"){
        //return true;
        //}



        //System.out.println(ipAddressAtTheEndOfTheTraceRoute);

        if (singleTraceRouteArray[singleTraceRouteArray.length - 1].contains(ipAddressAtTheBeginningOfTheTraceRoute) && !ipAddressAtTheBeginningOfTheTraceRoute.equals("IP NOT FOUND")) {
            System.out.println("Reachable route detected with IP: " + ipAddressAtTheBeginningOfTheTraceRoute);
            return true;
        }

        return false;
    }


    public static double totalDelayObtainedFromLastLineOfTraceRoute(String singleTraceRoute) {


        String lastLine = singleTraceRoute.split("\\r?\\n")[singleTraceRoute.split("\\r?\\n").length - 1];
        double timeInMs = 0;



        String arrayOfTimeAtTheEnd[] = lastLine.split(" ms");

        for (int i = 0; i < arrayOfTimeAtTheEnd.length; i++) {
            String timeInMsString = arrayOfTimeAtTheEnd[i].split(" ")[arrayOfTimeAtTheEnd[i].split(" ").length - 1];

            if (timeInMsString.trim().length() > 0 && !timeInMsString.trim().equals("*")) {
                timeInMs = timeInMs + Double.parseDouble(timeInMsString.trim());
                System.out.println("Hop  " + i + " on last line took " + Double.parseDouble(timeInMsString.trim()));
            }
        }
        System.out.println("Total delay at the last line of trace route is " + timeInMs);
        return timeInMs;

    }

    public static double totalNumberOfHopsOnTheLastLineOfTraceRoute(String singleTraceRoute) {


        String lastLine = singleTraceRoute.split("\\r?\\n")[singleTraceRoute.split("\\r?\\n").length - 1];
        double totalHops = 0;



        String arrayOfTimeAtTheEnd[] = lastLine.split(" ms");

        for (int i = 0; i < arrayOfTimeAtTheEnd.length; i++) {
            String timeInMsString = arrayOfTimeAtTheEnd[i].split(" ")[arrayOfTimeAtTheEnd[i].split(" ").length - 1];

            if (timeInMsString.trim().length() > 0 && !timeInMsString.trim().equals("*")) {
                totalHops = totalHops + 1;
                //System.out.println("Hop  " + i + " took " + Double.parseDouble(timeInMsString.trim()));
            }
        }

        System.out.println("Total number of hops at the last line of trace route is " + totalHops);
        return totalHops;


    }

    public static double averageDelay(String singleTraceRoute) {


        String lastLine = singleTraceRoute.split("\\r?\\n")[singleTraceRoute.split("\\r?\\n").length - 1];
        double timeInMs = 0;
        double totalHops = 0;


        String arrayOfTimeAtTheEnd[] = lastLine.split(" ms");
        System.out.println(Arrays.toString(arrayOfTimeAtTheEnd));
        System.out.println("length of array is" + arrayOfTimeAtTheEnd.length);
        for (int i = 0; i < arrayOfTimeAtTheEnd.length; i++) {
            String timeInMsString = arrayOfTimeAtTheEnd[i].split(" ")[arrayOfTimeAtTheEnd[i].split(" ").length - 1];
            
            if (timeInMsString.trim().length() > 0 && !timeInMsString.trim().equals("*")) {
                timeInMs = timeInMs + Double.parseDouble(timeInMsString.trim());
                System.out.println("TIME IS " + Double.parseDouble(timeInMsString.trim()));
                totalHops ++;
            }
        }
        System.out.println("Average delay for this route is " + timeInMs / arrayOfTimeAtTheEnd.length);
        return timeInMs /totalHops;

    }

    public static String getIPAddressFromString(String line) {
        //System.out.println(line);
        if (line.indexOf(")") != -1) {
            return line.substring(line.indexOf("(") + 1, line.indexOf(")"));
        }
        return "IP NOT FOUND";

    }


    public void outputFile(String traceRouteOutputString) throws IOException {
        //System.out.print(fileContent);
        //System.out.println(fileContentArray[1]);

        FileWriter writer = new FileWriter("C:\\Users\\admin\\Downloads\\output.txt");
        writer.write(traceRouteOutputString);
        writer.close();
    }

    public static double getNumberOfHops(String traceRoute) {
        //Doing -1 because the first line is "trace route to" which is not a hop
        return traceRoute.lines().count() - 1;
    }

}