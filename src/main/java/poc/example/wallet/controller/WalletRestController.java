package poc.example.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poc.example.wallet.model.Wallet;
import poc.example.wallet.repo.WalletRepo;

import java.util.List;

@RestController
@RequestMapping("rest/finance/wallet")
public class WalletRestController {

    @Autowired
    private WalletRepo walletDynamoRepo;

    @GetMapping
    public List<Wallet> findAll() {
        return walletDynamoRepo.findAll();
    }

    @GetMapping("/{id}")
    public Wallet findById(@PathVariable(value = "id") String id) {
        return walletDynamoRepo.findById(id);
    }

}
