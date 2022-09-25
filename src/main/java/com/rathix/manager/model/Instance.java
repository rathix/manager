package com.rathix.manager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

@Entity
@Data
public class Instance {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String url;
    private String iconPath;
    private String category = "default";
    private boolean isReachable;

    public void testReachable() {
        try {
            URL urlObj = new URL("https://" + url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("GET");
            // Set connection timeout
            con.setConnectTimeout(3000);
            con.connect();

            int code = con.getResponseCode();
            if (code == 200 || code == 401) {
                isReachable = true;
            }
        } catch (Exception e) {
            isReachable = false;
        }
    }
}
