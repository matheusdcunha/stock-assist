package cloud.matheusdcunha.stockassist.service;

import cloud.matheusdcunha.stockassist.domain.CsvStockItem;
import cloud.matheusdcunha.stockassist.entity.PurchaseRequestEntity;
import cloud.matheusdcunha.stockassist.repository.PurchaseRequestRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class StockAssistService {

    private final ReportService reportService;
    private final PurchaseSectorService purchaseSectorService;
    private final PurchaseRequestRepository purchaseRequestRepository;

    public StockAssistService(
            ReportService reportService,
            PurchaseSectorService purchaseSectorService,
            PurchaseRequestRepository purchaseRequestRepository
    ) {
        this.reportService = reportService;
        this.purchaseSectorService = purchaseSectorService;
        this.purchaseRequestRepository = purchaseRequestRepository;
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
                    var purchaseWithSuccess =  purchaseSectorService.sendPurchaseRequest(item, reorderQuantity);

                    // 3. Salvar no MongoDB os itens que foram recomprados
                    this.persist(item, reorderQuantity, purchaseWithSuccess);

                }
            });



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void persist(
            CsvStockItem item,
            Integer reorderQuantity,
            boolean purchaseWithSuccess
    ) {

        var entity = new PurchaseRequestEntity();
        entity.setId(item.getItemId());
        entity.setItemName(item.getItemName());
        entity.setQuantityOnStock(item.getQuantity());
        entity.setReorderThreshold(item.getReorderThreshold());
        entity.setSupplierEmail(item.getSupplierEmail());
        entity.setSupplierName(item.getSupplierName());
        entity.setLastStockUpdateTime(LocalDateTime.parse(item.getLastStockUpdateTime()));

        entity.setPurchaseQuantity(reorderQuantity);
        entity.setPurchaseWithSucess(purchaseWithSuccess);
        entity.setPurchaseDateTime(LocalDateTime.now());

        this.purchaseRequestRepository.save(entity);
    }

    private Integer calculateReorderQuantity(CsvStockItem item) {
        Double safetyMarginForRebuy = 0.2;
        return item.getReorderThreshold() + ((int) Math.ceil(item.getReorderThreshold() * safetyMarginForRebuy));
    }

}
