package com.example.gabfosol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Test {
    public JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }
    String[] links = new String[100];
    String[] names = new String[100];
    public void main(String[] args) throws JSONException {
        //new Post().postFun("https://weak-ruby-ladybug-robe.cyclic.app/niloy/register" , "{    \"handle\": \"value1\",\n    \"password\": \"value2\"}");


//        if(ja.length() == 0)System.out.println("lol");
//        else{
//            String verdict = ja.getJSONObject(0).getString("verdict");
//            JSONObject problem = ja.getJSONObject(0).getJSONObject("problem");
//            String problemLink = problem.getString("contestId") + problem.getString("index");
//            System.out.println(problemLink + verdict);
//        }

    }
}
