package manager;

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

public class PurchaseManager implements DBManager<PurchaseModel> {
    private static final Logger log = LoggerFactory.getLogger(PurchaseManager.class);
    @Override
    public List<PurchaseModel> get() {
        List<PurchaseModel> purchaseModels = new ArrayList<>();
        Connection connection = ConnectDB.getInstance().getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select purchase_id, product_id, consumer_id from purchase_sale.purchase");
            while (rs.next()) {
                purchaseModels.add(PurchaseModel.builder()
                        .setPurchaseId(rs.getInt("purchase_id"))
                        .setProductId(rs.getInt("product_id"))
                        .setConsumerId(rs.getInt("consumer_id"))
                        .build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return purchaseModels;
    }
}
