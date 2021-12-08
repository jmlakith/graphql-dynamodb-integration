package poc.example.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import poc.example.wallet.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, String> {
}
