package hei.school.file.endpoint.rest.controller.health;

import static hei.school.file.endpoint.rest.controller.health.PingController.KO;
import static hei.school.file.endpoint.rest.controller.health.PingController.OK;

import hei.school.file.PojaGenerated;
import hei.school.file.repository.DummyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PojaGenerated
@RestController
@AllArgsConstructor
public class HealthDbController {

  DummyRepository dummyRepository;

  @GetMapping("/health/db")
  public ResponseEntity<String> dummyTable_should_not_be_empty() {
    return dummyRepository.findAll().isEmpty() ? KO : OK;
  }
}
