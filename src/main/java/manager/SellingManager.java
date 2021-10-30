package manager;

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

public class SellingManager implements DBManager<SellingModel> {
    private static final Logger log = LoggerFactory.getLogger(SellingManager.class);
    @Override
    public List<SellingModel> get() {
        List<SellingModel> sellingModels = new ArrayList<>();
        Connection connection = ConnectDB.getInstance().getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select selling_id, product_id, provider_id from purchase_sale.selling");
            while (rs.next()) {
                sellingModels.add(SellingModel.builder()
                        .setSellingId(rs.getInt("selling_id"))
                        .setProductId(rs.getInt("product_id"))
                        .setProviderId(rs.getInt("provider_id"))
                        .build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return sellingModels;
    }
}
