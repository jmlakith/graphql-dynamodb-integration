package poc.example.wallet.service.dataFetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import poc.example.wallet.model.Wallet;
import poc.example.wallet.repo.WalletRepository;

@Component
public class WalletDataFetcher implements DataFetcher<Wallet> {
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Wallet get(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
        return walletRepository.findById(id).orElse(new Wallet());
    }
}
