package com.example.nightwatchv10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    public String SendPostRequest(String urlString, String bodey) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Authorization", Session.token);
            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = bodey.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            return con.getHeaderField("Location");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

    }
    public void SendPutRequest(String urlString, String bodey) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Authorization", Session.token);
            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = bodey.getBytes("utf-8");
                os.write(input, 0, input.length);
                os.flush();
            }
            con.connect();
            con.getResponseCode();
            //return con.getHeaderField("Location");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //return e.getMessage();
        }

    }
    public void SendDeleteRequest(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Authorization", Session.token);
            con.setDoOutput(true);
            con.getResponseCode();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public String SendGetRequest(String urlString, String param) {
        if (param == null) param = "";
        try {
            URL url = new URL(urlString + param);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", Session.token);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }



}
