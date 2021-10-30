import manager.ProductManager;
import manager.TownManager;
import model.ProductModel;
import model.TownModel;
import util.ConnectDB;
import util.HTMLWriter;

import java.util.List;

public class Main {
    public static void main(String[] argv) {

        TownManager townManager = new TownManager();
        ProductManager productManager = new ProductManager();

        List<TownModel> townModels = townManager.get();
        List<ProductModel> productModels = productManager.get();

        ConnectDB.getInstance().closeConnection();

        HTMLWriter htmlWriter = new HTMLWriter();
        htmlWriter.write(townModels, productModels);
    }
}
