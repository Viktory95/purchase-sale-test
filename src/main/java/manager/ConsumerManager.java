package manager;

import model.ConsumerModel;
import model.PurchaseModel;
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

public class ConsumerManager implements DBManager<ConsumerModel>{
    private static final Logger log = LoggerFactory.getLogger(ConsumerManager.class);
    private final PurchaseManager purchaseManager;

    public ConsumerManager() {
        this.purchaseManager = new PurchaseManager();
    }

    @Override
    public List<ConsumerModel> get() {
        List<ConsumerModel> consumerModels = new ArrayList<>();
        List<PurchaseModel> purchaseModels = purchaseManager.get();
        Connection connection = ConnectDB.getInstance().getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select consumer_id, consumer_name, town_id from purchase_sale.consumer");
            while (rs.next()) {
                final int consumerId = rs.getInt("consumer_id");
                consumerModels.add(ConsumerModel.builder()
                        .setConsumerId(consumerId)
                        .setConsumerName(rs.getString("consumer_name"))
                        .setTownId(rs.getInt("town_id"))
                        .setPurchaseModels(purchaseModels.stream().filter(p -> p.getConsumerId() == consumerId).collect(Collectors.toList()))
                        .build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return consumerModels;
    }
}
