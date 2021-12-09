package poc.example.wallet.repo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import poc.example.wallet.model.Wallet;

import java.util.List;

@Repository
public class WalletRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Wallet findById(String walletId) {
        return dynamoDBMapper.load(Wallet.class, walletId);
    }

    public Wallet save(Wallet wallet) {
        dynamoDBMapper.save(wallet);
        return wallet;
    }

    public List<Wallet> findAll() {
        return dynamoDBMapper.scan(Wallet.class, new DynamoDBScanExpression());
    }

}
