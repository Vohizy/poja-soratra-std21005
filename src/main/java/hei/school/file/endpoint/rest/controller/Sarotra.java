package hei.school.file.endpoint.rest.controller;

import lombok.AllArgsConstructor;
import org.flywaydb.core.internal.reports.html.HtmlReportGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class Sarotra {
    @PutMapping("/soratra/{id}")
        public ResponseEntity<Void> saveSoratra(@PathVariable String id, @RequestBody String soratra){
            if (containsUpperCaseLetter(soratra)) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }

            try {
                String filename = id + ".txt";
                File file = new File(filename);
                FileWriter writer = new FileWriter(file);
                writer.write(soratra);
                writer.close();

                sendToFileBucket(filename, file);

                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (IOException e) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

    private boolean containsUpperCaseLetter(String text) {
        return text != null && !text.equals(text.toLowerCase());
    }
}
