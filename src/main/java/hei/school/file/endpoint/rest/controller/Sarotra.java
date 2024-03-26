package hei.school.file.endpoint.rest.controller;

import hei.school.file.file.BucketComponent;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
        public ResponseEntity<Void> saveSoratra(@PathVariable String id, @RequestBody String soratra){
        if (!isLowerCase(Integer.parseInt(soratra))) {
            // Si la phrase poétique n'est pas entièrement en minuscules, retourner un HTTP 200 OK avec un corps vide
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        try {
            // Création du fichier .txt avec la phrase poétique
            String filename = id + ".txt";
            File file = new File(filename);
            FileWriter writer = new FileWriter(file);
            writer.write(soratra);
            writer.close();

            // Envoi du fichier dans le bucket
            bucketComponent.upload(file, filename);

            // Retourner un HTTP 200 OK si tout s'est bien déroulé
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IOException e) {
            // En cas d'erreur lors de l'écriture du fichier ou de l'envoi dans le bucket, retourner un HTTP 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        private boolean isLowerCase(String text) {
            return text != null && !text.isEmpty() && text.equals(text.toLowerCase());
        }
}
