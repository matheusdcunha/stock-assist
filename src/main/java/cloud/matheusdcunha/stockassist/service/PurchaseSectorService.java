package cloud.matheusdcunha.stockassist.service;

import cloud.matheusdcunha.stockassist.domain.CsvStockItem;
import org.springframework.stereotype.Service;

@Service
public class PurchaseSectorService {

    private final AuthService authService;

    public PurchaseSectorService(AuthService authService) {
        this.authService = authService;
    }

    public boolean sendPurchaseRequest(CsvStockItem item, Integer purchaseQuantity){

        // TODO

        // 1. Autenticação na API para recuperar o token
        var token = authService.getToken();

        // 2. Enviar a solicitação de compra com o token gerado na chamada anterior

        // 3. Validar se a resposta veio com sucesso


        return  false;
    }

}
