package cloud.matheusdcunha.stockassist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<Map<String, String>> getHealth(){

        var date = LocalDateTime.now().toString();
        var message = "Application Health";

        Map<String, String> response = Map.of("message", message, "date", date);

        return ResponseEntity.ok().body(response);
    }
}
