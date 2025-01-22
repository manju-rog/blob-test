package com.example.sso;



import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class AzureBlobConfig {
    @Value("${azure.storage.account-name}")
    private String storageAccount;

    @Value("${azure.storage.container-name}")
    private String containerName;

    @Value("${azure.storage.sas-token}")
    private String sasToken;

    public String getStorageAccount() { return storageAccount; }
    public String getContainerName() { return containerName; }
    public String getSasToken() { return sasToken; }
}
