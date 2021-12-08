package poc.example.wallet.service.dataFetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import poc.example.wallet.model.Wallet;
import poc.example.wallet.repo.WalletRepository;

import java.util.List;

@Component
public class AllWalletsDataFetcher implements DataFetcher<List<Wallet>> {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<Wallet> get(DataFetchingEnvironment environment) {

        return walletRepository.findAll();
    }
}
