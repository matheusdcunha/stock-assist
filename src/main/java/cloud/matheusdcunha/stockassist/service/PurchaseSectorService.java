package cloud.matheusdcunha.stockassist.service;

import cloud.matheusdcunha.stockassist.client.PurchaseSectorClient;
import cloud.matheusdcunha.stockassist.client.dto.PurchaseRequest;
import cloud.matheusdcunha.stockassist.domain.CsvStockItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PurchaseSectorService {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseSectorService.class);

    private final AuthService authService;
    private final PurchaseSectorClient purchaseSectorClient;

    public PurchaseSectorService(AuthService authService, PurchaseSectorClient purchaseSectorClient) {
        this.authService = authService;
        this.purchaseSectorClient = purchaseSectorClient;
    }

    public boolean sendPurchaseRequest(CsvStockItem item, Integer purchaseQuantity){

        // TODO
        // 1. Autenticação na API para recuperar o token
        var token = authService.getToken();

        // 2. Enviar a solicitação de compra com o token gerado na chamada anterior
        var request = new PurchaseRequest(
                item.getItemId(),
                item.getItemName(),
                item.getSupplierName(),
                item.getSupplierEmail(),
                purchaseQuantity
        );

        var response = this.purchaseSectorClient.sendPurchaseRequest(token, request);

        // 3. Validar se a resposta veio com sucesso
        if (response.getStatusCode().value() != HttpStatus.ACCEPTED.value()) {
            logger.error("error while sending purchase request, status {}, response: {}", response.getBody(), response.getStatusCode());

            return false;
        }

        return  true;
    }

}
