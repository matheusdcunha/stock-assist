package cloud.matheusdcunha.stockassist.service;

import cloud.matheusdcunha.stockassist.client.AuthClient;
import cloud.matheusdcunha.stockassist.client.dto.AuthRequest;
import cloud.matheusdcunha.stockassist.config.AppConfig;
import cloud.matheusdcunha.stockassist.exception.StockAssistException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private static final String GRANT_TYPE = "client_credentials";
    private static String token;
    private static LocalDateTime expiresIn;

    private final AuthClient authClient;
    private final AppConfig appConfig;

    public AuthService(AuthClient authClient, AppConfig appConfig) {
        this.authClient = authClient;
        this.appConfig = appConfig;
    }

    public String getToken(){

        if(token  ==  null){
            this.generateToken();
        }

        if(expiresIn.isBefore(LocalDateTime.now())){
            this.generateToken();
        }

        return token;
    }

    private void generateToken() {

        var request = new AuthRequest(
                GRANT_TYPE,
                appConfig.getClientId(),
                appConfig.getClientSecret()
        );

        var response = authClient.authenticate(request);

        if (!response.getStatusCode().is2xxSuccessful()){
            throw new StockAssistException("cannot generate token" +
                    " status: " + response.getStatusCode() +
                    " response: " + response.getBody()
                    );
        }

        token = response.getBody().accessToken();
        expiresIn = LocalDateTime.now().plusSeconds(response.getBody().expiresIn());
    }
}
