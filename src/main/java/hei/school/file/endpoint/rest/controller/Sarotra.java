package hei.school.file.endpoint.rest.controller;

import hei.school.file.file.BucketComponent;
import lombok.AllArgsConstructor;
import org.apache.tomcat.websocket.TransformationResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import static java.awt.SystemColor.text;
import static java.lang.Character.isLowerCase;

@RestController
@AllArgsConstructor
public class Sarotra {
    BucketComponent bucketComponent;
    @PutMapping("/soratra/{id}")
        public ResponseEntity<Void> saveSoratra(@PathVariable String id, @RequestBody String soratra) {
        if (!isLowerCase(Integer.parseInt(soratra))) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        try {
            String filename = id + ".txt";
            File file = new File(filename);
            FileWriter writer = new FileWriter(file);
            writer.write(soratra);
            writer.close();
            bucketComponent.upload(file, filename);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}

