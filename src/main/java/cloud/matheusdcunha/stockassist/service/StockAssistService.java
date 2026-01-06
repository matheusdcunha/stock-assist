package cloud.matheusdcunha.stockassist.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StockAssistService {

    private final ReportService reportService;

    public StockAssistService(ReportService reportService) {
        this.reportService = reportService;
    }

    public void start(String reportPath){

        // TODO
        // 1. Ler arquivo CSV

        try {
            var item = reportService.readStockReport(reportPath);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 2. Para cada item do CSV chamar a API do setor de compras

        // 3. Salvar no MongoDB os itens que foram recomprados
    }

}
