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

    public static void main(String[] args) throws JSONException {
        String names = "niloy , sejuti , miraz , mofa";
        String []nameArray = names.split("\\s*,\\s*");
        System.out.println(nameArray.length);
        for(String name:nameArray) System.out.println(name);
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
