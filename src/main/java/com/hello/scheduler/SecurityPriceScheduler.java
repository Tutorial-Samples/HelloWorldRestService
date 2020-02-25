package com.hello.scheduler;


import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Component
public class SecurityPriceScheduler {

    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000);
        try{
            URL url = new URL("https://financialmodelingprep.com/api/v3/company/profile/AAPL");
            //URL url = new URL("http://localhost:8080/university/api/departments");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (con.getInputStream())));
            String jsonText = readAll(br);
            JSONObject json = new JSONObject(jsonText);
            System.out.println(json);
            System.out.println(json.get("symbol"));
            JSONObject profileObject = json.getJSONObject("profile");
            System.out.println(profileObject.get("website"));
            //JSONObject profile = new JSONObject(json.get("profile"));
            //System.out.println(profile);
            //System.out.println(profile.get("website"));
//            String output;
//            System.out.println("Output from Server .... \n");
//            while ((output = br.readLine()) != null) {
//                System.out.println(output);
//            }

            con.disconnect();
        }catch(MalformedURLException mue){
            mue.printStackTrace();
        }catch(ProtocolException pe){
            pe.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
