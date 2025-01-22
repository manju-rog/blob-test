package com.example.sso;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class BlobService {
    @Autowired
    private AzureBlobConfig config;

    public String readBlobContent(String blobName) throws Exception {
        String blobUrl = String.format(
            "https://%s.blob.core.windows.net/%s/%s%s",
            config.getStorageAccount(),
            config.getContainerName(),
            blobName,
            config.getSasToken()
        );

        URL url = new URL(blobUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        StringBuilder content = new StringBuilder();
        
        try {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine).append("\n");
                    }
                }
            } else {
                throw new RuntimeException("Failed read blob --> Response code: " 
                    + connection.getResponseCode());
            }
        } finally {
            connection.disconnect();
        }
        
        return content.toString();
    }
}