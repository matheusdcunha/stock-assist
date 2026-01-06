package cloud.matheusdcunha.stockassist.service;

import cloud.matheusdcunha.stockassist.domain.CsvStockItem;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StockAssistService {

    private final ReportService reportService;
    private final PurchaseSectorService purchaseSectorService;

    public StockAssistService(
            ReportService reportService,
            PurchaseSectorService purchaseSectorService
    ) {
        this.reportService = reportService;
        this.purchaseSectorService = purchaseSectorService;
    }

    public void start(String reportPath){

        // TODO
        // 1. Ler arquivo CSV

        try {
            var items = reportService.readStockReport(reportPath);

            items.forEach(item -> {
                if(item.getQuantity() < item.getReorderThreshold()){

                    // 1 . Calcular a quantidade a ser recomprada
                    var reorderQuantity = calculateReorderQuantity(item);

                    // 2. Para cada item do CSV chamar a API do setor de compras
                    purchaseSectorService.sendPurchaseRequest(item, reorderQuantity);

                    // 3. Salvar no MongoDB os itens que foram recomprados

                }
            });



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer calculateReorderQuantity(CsvStockItem item) {
        Double safetyMarginForRebuy = 0.2;
        return item.getReorderThreshold() + ((int) Math.ceil(item.getReorderThreshold() * safetyMarginForRebuy));
    }

}
