package poc.example.wallet.service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import poc.example.wallet.model.Wallet;
import poc.example.wallet.repo.WalletRepository;
import poc.example.wallet.service.dataFetcher.AllWalletsDataFetcher;
import poc.example.wallet.service.dataFetcher.WalletDataFetcher;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class GraphQLService {

    @Autowired
    private WalletRepository repo;

    @Value("classpath:wallets.graphql")
    Resource resource;

    private GraphQL graphQL;
    @Autowired
    private AllWalletsDataFetcher allWalletsDataFetcher;
    @Autowired
    private WalletDataFetcher walletDataFetcher;



    @PostConstruct
    void loadSchema() throws IOException {
        //Seed data for testing purposes
        seedDataHSQL();
        //Fetch the schema
        File schemaFile = resource.getFile();
        //Parse schema
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void seedDataHSQL() {
        Stream.of(
                        new Wallet("123", "id123", "my_wallet1", "CREDIT"),
                        new Wallet("124", "id124", "my_wallet2", "POINT"),
                        new Wallet("125", "id125", "my_wallet3", "CREDIT")
                )
                .forEach(wallet -> repo.save(wallet));
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring ->
                        typeWiring.dataFetcher("allWallets", allWalletsDataFetcher)
                                .dataFetcher("wallet", walletDataFetcher)
                )
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
