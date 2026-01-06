package cloud.matheusdcunha.stockassist.client;

import cloud.matheusdcunha.stockassist.client.dto.AuthRequest;
import cloud.matheusdcunha.stockassist.client.dto.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AuthClient", url = "${api.auth-url}")
public interface AuthClient {

    @PostMapping("/api/token")
    ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request);

}
