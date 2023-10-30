package com.example.gabfosol;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Post {
    public static void postFun(String uri , String postData){
        try {
            // URL of the API you want to send the POST request to
            //URL url = new URL(base + "niloy/register");

            // Open a connection to the URL
            //URL url = new URL("https://weak-ruby-ladybug-robe.cyclic.app/niloy/register"); // Replace with your actual URL
            URL url = new URL(uri);
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Enable input/output streams
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Set request headers (optional)
            connection.setRequestProperty("Content-Type", "application/json");

            // Prepare the data to be sent in the request body
            //String postData = "{\"handle\":\"your_handle\",\"password\":\"your_password\"}";

            // Write the data to the request body
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(postData);
            osw.close();
            os.close();

            // Get the response from the server
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Handle the response (read from the connection's input stream)

            connection.disconnect(); // Close the connection

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
