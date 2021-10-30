package manager;

import model.ProductModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductManager implements DBManager<ProductModel>{
    private static final Logger log = LoggerFactory.getLogger(ProductManager.class);
    @Override
    public List<ProductModel> get() {
        List<ProductModel> productModels = new ArrayList<>();
        Connection connection = ConnectDB.getInstance().getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select product_id, product_name from purchase_sale.product");
            while (rs.next()) {
                productModels.add(ProductModel.builder()
                        .setProductId(rs.getInt("product_id"))
                        .setProductName(rs.getString("product_name"))
                        .build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return productModels;
    }
}
