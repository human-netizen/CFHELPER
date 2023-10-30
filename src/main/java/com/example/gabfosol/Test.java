package com.example.gabfosol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {
    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }
    public static void main(String[] args) throws JSONException {
        //JsonParse jp = new JsonParse();
        new Post().postFun("https://weak-ruby-ladybug-robe.cyclic.app/niloy/register" , "{    \"handle\": \"value1\",\n    \"password\": \"value2\"}");


//        //JSONArray ja = jp.res("https://codeforces.com/api/user.status?handle=dragoonSlayer&from=1&count=1");
//        if(ja.length() == 0)System.out.println("lol");
//        else{
//            String verdict = ja.getJSONObject(0).getString("verdict");
//            JSONObject problem = ja.getJSONObject(0).getJSONObject("problem");
//            String problemLink = problem.getString("contestId") + problem.getString("index");
//            System.out.println(problemLink + verdict);
//        }

    }
}
