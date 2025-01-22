package com.example.sso;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/blob")
public class BlobController {
    @Autowired
    private BlobService blobService;

    @GetMapping("/read/{blobName}")
    public String readBlob(@PathVariable String blobName) {
        try {
            return blobService.readBlobContent(blobName);
        } catch (Exception e) {
            return "Error reading blob: " + e.getMessage();
        }
    }
}