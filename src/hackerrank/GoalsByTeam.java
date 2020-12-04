package hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoalsByTeam {

    public static void main(String[] args) {
        final int totalGoalsForGivenTeam = getTotalGoals("Barcelona", 2011);
        System.out.println("totalGoalsForGivenTeam = " + totalGoalsForGivenTeam);

    }

    public static int getTotalGoals(String team, int year)  {
        final String defaultMatchDetailsForHomeTeam = getFooballMatcheDetails(team, year, true, 1);
        final int totalPagesForHomeTeam = parseTotalPages(defaultMatchDetailsForHomeTeam);

        System.out.println("totalPagesForHomeTeam = " + totalPagesForHomeTeam);

        int totalGoalsForHomeTeam = parseTeamGoals(defaultMatchDetailsForHomeTeam, true);


        for (int i = 2; i <= totalPagesForHomeTeam; i ++) {
            System.out.println("Calling home team api: " + i);

            final String matchDetailsForHomeTeam = getFooballMatcheDetails(team, year, true, i);
            totalGoalsForHomeTeam = totalGoalsForHomeTeam + parseTeamGoals(matchDetailsForHomeTeam, true);
        }

        System.out.println("totalGoalsForHomeTeam = " + totalGoalsForHomeTeam);

        final String defaultMatchDetailsForVisitingTeam = getFooballMatcheDetails(team, year, false, 1);
        final int totalPagesForVisitingTeam = parseTotalPages(defaultMatchDetailsForVisitingTeam);

        System.out.println("totalPagesForVisitingTeam = " + totalPagesForVisitingTeam);

        int totalGoalsForVisitingTeam = parseTeamGoals(defaultMatchDetailsForVisitingTeam, false);



        for (int i = 2; i <= totalPagesForVisitingTeam; i ++) {
            System.out.println("Calling home visiting api: " + i);

            final String matchDetailsForVisitingTeam = getFooballMatcheDetails(team, year, false, i);
            totalGoalsForVisitingTeam = totalGoalsForVisitingTeam + parseTeamGoals(matchDetailsForVisitingTeam, true);
        }

        System.out.println("totalGoalsForVisitingTeam = " + totalGoalsForVisitingTeam);


        return totalGoalsForHomeTeam + totalGoalsForVisitingTeam;
    }

    private static int parseTeamGoals(String matchDetails, boolean isHomeTeam) {
        Pattern pattern = isHomeTeam ? Pattern.compile("\"team1goals\".\"[0-9]*") : Pattern.compile("\"team2goals\".\"[0-9]*");
        Matcher matcher = pattern.matcher(matchDetails);

        int totalGoals = 0;

        while (matcher.find()) {
            int currentGoals = Integer.parseInt(matcher.group().split(":\"")[1]);
            totalGoals = totalGoals + currentGoals;
        }

        return totalGoals;
    }

    private static int parseTotalPages(String matchDetails) {
        Pattern pattern = Pattern.compile("\"total_pages\".[0-9]*");
        Matcher matcher = pattern.matcher(matchDetails);

        int totalPages = 0;

        while (matcher.find()) {
            //System.out.println(matcher.group());
            totalPages = Integer.parseInt(matcher.group().split(":")[1]);
            //System.out.println(totalPages);
        }

        return totalPages;
    }

    public static String getFooballMatcheDetails(String team, int year, boolean isHomeTeam, int pageNum)  {
        String output;
        StringBuilder sb = new StringBuilder();

        try {

            StringBuilder urlBuilder = new StringBuilder("https://jsonmock.hackerrank.com/api/football_matches?");
            urlBuilder.append("year=").append(year);

            if (isHomeTeam) {
                urlBuilder.append("&team1=").append(team);
            } else {
                urlBuilder.append("&team2=").append(team);
            }

            urlBuilder.append("&page=").append(pageNum);

            System.out.println("URL = " + urlBuilder);

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            conn.disconnect();
        } catch (Exception exception) {
            System.out.println("Exception Occurred: " + exception);

        }

        System.out.println("Response = " + sb);

        return  sb.toString();

    }

}
