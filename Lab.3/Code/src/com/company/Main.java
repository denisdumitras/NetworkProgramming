package com.company;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class Main {

    static void GetMethod() throws IOException {
        URL url = new URL("http://httpbin.org/ip");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();

        BufferedReader streamReader;
        String inputLine;
        StringBuffer response = new StringBuffer();

        if (status > 299) {
            streamReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        else {
            streamReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }

        while ((inputLine = streamReader.readLine()) != null) {
            response.append(inputLine);
            response.append("\n");
        }

        streamReader.close();

        con.disconnect();

        System.out.println(response.toString());
        System.out.println("-------------------------------------------------------");
    }

    static void PostMethod() throws IOException{
        URL url = new URL("https://httpbin.org/post");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("comments", "Deliver it hot!");
        parameters.put("custemail", "paco.alcacer@yahoo.com");
        parameters.put("custname", "Paco Alcacer");
        parameters.put("custtel", "+1409519829018");
        parameters.put("delivery", "18:00");
        parameters.put("size", "large");
        parameters.put("topping", "cheese");

        con.setDoOutput(true);

        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

        int status = con.getResponseCode();

        BufferedReader streamReader;
        String inputLine;
        StringBuffer response = new StringBuffer();

        if (status > 299) {
            streamReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        else {
            streamReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }

        while ((inputLine = streamReader.readLine()) != null) {
            response.append(inputLine);
            response.append("\n");
        }

        streamReader.close();

        con.disconnect();

        System.out.println(response.toString() + "\n");
        System.out.println("-------------------------------------------------------");
    }

    static void DeleteMethod() throws IOException{
        URL url = new URL("http://httpbin.org/delete");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "val");

        con.setDoOutput(true);

        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

        int status = con.getResponseCode();

        BufferedReader streamReader;
        String inputLine;
        StringBuffer response = new StringBuffer();

        if (status > 299) {
            streamReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        else {
            streamReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }

        while ((inputLine = streamReader.readLine()) != null) {
            response.append(inputLine);
            response.append("\n");
        }

        streamReader.close();

        con.disconnect();

        System.out.println(response.toString());
        System.out.println("-------------------------------------------------------");
    }

    static void PutMethod() throws IOException{
        URL url = new URL("http://httpbin.org/put");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "val");

        con.setDoOutput(true);

        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

        int status = con.getResponseCode();

        BufferedReader streamReader;
        String inputLine;
        StringBuffer response = new StringBuffer();

        if (status > 299) {
            streamReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        else {
            streamReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }

        while ((inputLine = streamReader.readLine()) != null) {
            response.append(inputLine);
            response.append("\n");
        }

        streamReader.close();

        con.disconnect();

        System.out.println(response.toString());
        System.out.println("-------------------------------------------------------");
    }

    static void GetImage() throws IOException{
        Image image = null;

        URL url = new URL("http://httpbin.org/image/png");

//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");

        image = ImageIO.read(url);

        JFrame frame = new JFrame();
        frame.setSize(250, 250);
        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
    }

    static void PatchMethod() throws IOException{
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPatch patchRequest = new HttpPatch("http://httpbin.org/patch");
        HttpResponse response = client.execute(patchRequest);

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.lineSeparator());
        }

        System.out.println(builder);
    }

    public static void main(String[] args) throws IOException{
        GetMethod();
        PostMethod();
        DeleteMethod();
        PutMethod();
        GetImage();
        PatchMethod();
    }
}
