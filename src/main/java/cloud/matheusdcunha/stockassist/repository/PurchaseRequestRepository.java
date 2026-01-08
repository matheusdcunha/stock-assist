package cloud.matheusdcunha.stockassist.repository;


import cloud.matheusdcunha.stockassist.entity.PurchaseRequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRequestRepository extends MongoRepository<PurchaseRequestEntity, String> {
}
