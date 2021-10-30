package util;

import model.ConsumerModel;
import model.ProductModel;
import model.ProviderModel;
import model.TownModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HTMLWriter {
    private static final Logger log = LoggerFactory.getLogger(HTMLWriter.class);
    public void write(List<TownModel> townModels, List<ProductModel> productModels) {
        File resultFile = new File(PropertiesProvider.getInstance().getProperty("result.file.path"));
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
            bufferedWriter.write("<html><body><table><tr>\n" +
                    "    <th>Город</th>\n" +
                    "    <th>Товар</th>\n" +
                    "    <th>Поставщик</th>\n" +
                    "    <th>Потребитель</th>\n" +
                    "   </tr>");


            for (TownModel townModel : townModels) {
                boolean isEmpty = townModel.getProviderModels().stream().anyMatch(p -> p.getSellingModels().stream()
                        .anyMatch(s -> townModel.getConsumerModels().stream()
                                .anyMatch(c -> c.getPurchaseModels().stream()
                                        .anyMatch(pu -> pu.getProductId() == s.getProductId()))))
                        && townModel.getConsumerModels().stream().anyMatch(p -> p.getPurchaseModels().stream()
                        .anyMatch(s -> townModel.getProviderModels().stream()
                                .anyMatch(c -> c.getSellingModels().stream()
                                        .anyMatch(pu -> pu.getProductId() == s.getProductId()))));
                if (isEmpty) {
                    bufferedWriter.write("<tr><td>"
                            + townModel.getTownName()
                            + "</td><td></td><td></td><td></td></tr>\n");

                    for (ProductModel productModel : productModels) {
                        List<ProviderModel> filteredProviderModels = townModel.getProviderModels().stream()
                                .filter(p -> p.getSellingModels().stream().anyMatch(s -> s.getProductId() == productModel.getProductId()))
                                .collect(Collectors.toList());
                        List<ConsumerModel> filteredConsumerModels = townModel.getConsumerModels().stream()
                                .filter(c -> c.getPurchaseModels().stream().anyMatch(p -> p.getProductId() == productModel.getProductId()))
                                .collect(Collectors.toList());
                        if (filteredProviderModels.size() > 0
                                && filteredConsumerModels.size() > 0) {
                            bufferedWriter.write("<tr><td></td><td>"
                                    + productModel.getProductName()
                                    + "</td><td></td><td></td></tr>\n");

                            int size = Math.max(filteredProviderModels.size(), filteredConsumerModels.size());
                            for (int i = 0; i < size; i++) {
                                bufferedWriter.write("<tr><td></td><td></td><td>"
                                        + (i >= filteredProviderModels.size() ? "" : filteredProviderModels.get(i).getProviderName())
                                        + "</td><td>"
                                        + (i >= filteredConsumerModels.size() ? "" : filteredConsumerModels.get(i).getConsumerName())
                                        + "</td></tr>\n");
                            }
                        }
                    }
                }
            }

            bufferedWriter.write("</table></body></html>");
            bufferedWriter.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
