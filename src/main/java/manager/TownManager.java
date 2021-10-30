package manager;

import model.ConsumerModel;
import model.ProviderModel;
import model.TownModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TownManager implements DBManager<TownModel> {
    private static final Logger log = LoggerFactory.getLogger(TownManager.class);
    private final ProviderManager providerManager;
    private final ConsumerManager consumerManager;

    public TownManager() {
        this.consumerManager = new ConsumerManager();
        this.providerManager = new ProviderManager();
    }

    @Override
    public List<TownModel> get() {
        List<TownModel> townModels = new ArrayList<>();
        List<ProviderModel> providerModels = providerManager.get();
        List<ConsumerModel> consumerModels = consumerManager.get();
        Connection connection = ConnectDB.getInstance().getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select town_id, town_name from purchase_sale.town");
            while (rs.next()) {
                final int townId = rs.getInt("town_id");
                townModels.add(TownModel.builder()
                        .setTownId(townId)
                        .setTownName(rs.getString("town_name"))
                        .setProviderModels(providerModels.stream().filter(p -> p.getTownId() == townId).collect(Collectors.toList()))
                        .setConsumerModels(consumerModels.stream().filter(c -> c.getTownId() == townId).collect(Collectors.toList()))
                        .build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return townModels;
    }
}
