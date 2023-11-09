package com.example.gabfosol;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.*;
import java.net.*;
import java.util.*;

public class Test2 {

    // A class to store the problem information
    static class Problem {
        String contestId;
        String index;
        String name;
        int rating;

        public Problem(String contestId, String index, String name, int rating) {
            this.contestId = contestId;
            this.index = index;
            this.name = name;
            this.rating = rating;
        }

        // A method to return the problem URL
        public String getURL() {
            return "https://codeforces.com/contest/" + contestId + "/problem/" + index;
        }

        // A method to return the problem information as a string
        public String toString() {
            return name + " (" + rating + ") - " + getURL();
        }
    }

    // A method to get the JSON object from a URL
    public static JSONObject getJSON(String url) throws IOException, JSONException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return new JSONObject(response.toString());
    }

    // A method to get the time when the user first reached a rating
    public static long getTime(String handle, int rating) throws IOException, JSONException {
        // Get the user rating history from the API
        String url = "https://codeforces.com/api/user.rating?handle=" + handle;
        JSONObject json = getJSON(url);

        // Check if the status is OK
        if (!json.getString("status").equals("OK")) {
            System.out.println("Invalid handle or API error");
            return -1;
        }

        // Get the result array
        JSONArray result = json.getJSONArray("result");

        // Loop through the result array and find the first contest where the user reached the rating
        for (int i = 0; i < result.length(); i++) {
            JSONObject contest = result.getJSONObject(i);
            int newRating = contest.getInt("newRating");
            if (newRating >= rating) {
                // Return the time of the contest
                return contest.getLong("ratingUpdateTimeSeconds");
            }
        }

        // If no such contest exists, return -1
        System.out.println("The user never reached " + rating + " rating");
        return -1;
    }

    // A method to get the problems that were solved by the user between two times
    public static ArrayList<Problem> getProblems(String handle, long time1, long time2) throws IOException, JSONException {
        // Get the user status from the API
        String url = "https://codeforces.com/api/user.status?handle=" + handle;
        JSONObject json = getJSON(url);

        // Check if the status is OK
        if (!json.getString("status").equals("OK")) {
            System.out.println("Invalid handle or API error");
            return null;
        }

        // Get the result array
        JSONArray result = json.getJSONArray("result");

        // Create a hash set to store the solved problems
        HashSet<String> solved = new HashSet<>();

        // Create an array list to store the problems that were solved between time1 and time2
        ArrayList<Problem> problems = new ArrayList<>();

        // Loop through the result array and find the problems that were solved between time1 and time2
        for (int i = 0; i < result.length(); i++) {
            JSONObject submission = result.getJSONObject(i);
            long creationTimeSeconds = submission.getLong("creationTimeSeconds");
            if (creationTimeSeconds >= time1 && creationTimeSeconds <= time2) {
                // Get the problem information from the submission
                JSONObject problem = submission.getJSONObject("problem");
                String contestId = "0";
                if(problem.has("contestId"))contestId = problem.getString("contestId");
                else{
                    System.out.println();
                    System.out.println(problem.getString("name"));
                    continue;
                }
                String index = problem.getString("index");
                String name = problem.getString("name");
                int rating = 0;
                if (problem.has("rating")) {
                    rating = problem.getInt("rating");
                    // Do something with the rating
                }


                // Create a problem object
                Problem p = new Problem(contestId, index, name, rating);

                // Check if the problem is already solved by the user
                String key = contestId + "-" + index;
                if (!solved.contains(key)) {
                    // Add the problem to the solved set and the problems list
                    solved.add(key);
                    problems.add(p);
                }
            }
        }

        // Return the problems list
        return problems;
    }

    public static void main(String[] args) throws IOException, JSONException {
        // Take the handle and two ratings as input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the handle:");
        String handle = sc.next();
        System.out.println("Enter the first rating:");
        int rating1 = sc.nextInt();
        System.out.println("Enter the second rating:");
        int rating2 = sc.nextInt();
        sc.close();

        // Get the times when the user first reached the ratings
        long time1 = getTime(handle, rating1);
        long time2 = getTime(handle, rating2);

        // Check if the times are valid
        if (time1 == -1 || time2 == -1) {
            System.out.println("Cannot find the problems");
            return;
        }

        // Get the problems that were solved by the user between the times
        ArrayList<Problem> problems = getProblems(handle, time1, time2);

        // Check if the problems list is empty
        if (Objects.requireNonNull(problems).isEmpty()) {
            System.out.println("No problems were solved by the user between the times");
            return;
        }

        // Print the problems
        System.out.println("The problems that were solved by " + handle + " between the times when they first reached " + rating1 + " and " + rating2 + " ratings are:");
        for (Problem p : problems) {
            System.out.println(p);
        }
    }
}

