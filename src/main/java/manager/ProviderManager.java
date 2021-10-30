package manager;

import model.ProviderModel;
import model.SellingModel;
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

public class ProviderManager implements DBManager<ProviderModel> {
    private static final Logger log = LoggerFactory.getLogger(ProviderManager.class);
    private final SellingManager sellingManager;

    public ProviderManager() {
        this.sellingManager = new SellingManager();
    }
    @Override
    public List<ProviderModel> get() {
        List<ProviderModel> providerModels = new ArrayList<>();
        List<SellingModel> sellingModels = sellingManager.get();
        Connection connection = ConnectDB.getInstance().getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select provider_id, provider_name, town_id from purchase_sale.provider");
            while (rs.next()) {
                final int providerId = rs.getInt("provider_id");
                providerModels.add(ProviderModel.builder()
                        .setProviderId(providerId)
                        .setProviderName(rs.getString("provider_name"))
                        .setTownId(rs.getInt("town_id"))
                        .setSellingModels(sellingModels.stream().filter(s -> s.getProviderId() == providerId).collect(Collectors.toList()))
                        .build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return providerModels;
    }
}
