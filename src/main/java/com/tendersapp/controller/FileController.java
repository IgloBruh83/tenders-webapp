package com.tendersapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class FileController {

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(
            @PathVariable String filename
    ) {
        try {
            Path file = Paths.get("upload-dir").resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + filename + "\"")
                    .body(resource);
        } catch (MalformedURLException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Файл не знайдено", ex);
        }
    }

}
