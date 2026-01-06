package cloud.matheusdcunha.stockassist.controller;

import cloud.matheusdcunha.stockassist.controller.dto.StartDto;
import cloud.matheusdcunha.stockassist.service.StockAssistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class StartController {

    private final StockAssistService stockAssistService;

    public StartController(StockAssistService stockAssistService) {
        this.stockAssistService = stockAssistService;
    }

    @PostMapping(path = "/start")
    public ResponseEntity<Void> start(@RequestBody StartDto dto){

        CompletableFuture.runAsync(()->{
            stockAssistService.start(dto.reportPath());
        });


        return ResponseEntity.accepted().build();
    }
}

