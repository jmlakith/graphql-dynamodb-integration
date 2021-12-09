package poc.example.wallet.service.dataFetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import poc.example.wallet.model.Wallet;
import poc.example.wallet.repo.WalletRepo;

@Component
public class WalletDataFetcher implements DataFetcher<Wallet> {

    @Autowired
    private WalletRepo walletRepo;

    @Override
    public Wallet get(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");

        return walletRepo.findById(id);
    }
}
