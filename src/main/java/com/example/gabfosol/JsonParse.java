package com.example.gabfosol;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author niloyd
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParse {

    public void JsonParse(){

    }

    public JSONArray res(String stringUrl){
        JSONArray result = new JSONArray();
        try {
            // Create a URL object with the given URL
            URL url = new URL(stringUrl);

            // Open a connection to the URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            conn.setRequestMethod("GET");

            // Set the request header to accept JSON data
            conn.setRequestProperty("Accept", "application/json");

            // Check the response code
            if (conn.getResponseCode() != 200) {
                // If the response code is not 200, throw an exception
                //throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                System.out.println("FAILED");
                return result;
            }


            // Create a buffered reader to read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            // Create a string builder to store the response
            StringBuilder sb = new StringBuilder();

            // Read each line of the response and append it to the string builder
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            // Close the buffered reader
            br.close();

            // Close the connection
            conn.disconnect();

            // Convert the string builder to a string
            String jsonString = sb.toString();

            // Create a JSON object from the string
            JSONObject obj = new JSONObject(jsonString);

            // Get the value of the "status" key
            String status = obj.getString("status");

            // Print the status
            System.out.println("Status: " + status);

            // Get the value of the "result" key, which is an array of objects
            result = obj.getJSONArray("result");

            // Loop through each element of the array

        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
        return result;
    }
}


