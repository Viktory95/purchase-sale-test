package model;

import java.util.ArrayList;
import java.util.List;

public class ConsumerModel {
    private int consumerId;
    private String consumerName;
    private int townId;
    private List<PurchaseModel> purchaseModels;

    public int getConsumerId() {
        return consumerId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public int getTownId() {
        return townId;
    }

    public List<PurchaseModel> getPurchaseModels() {
        return new ArrayList<>(purchaseModels);
    }

    private ConsumerModel() {
    }

    public static ConsumerModel.Builder builder() {
        return new ConsumerModel().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public ConsumerModel.Builder setConsumerId(int consumerId) {
            ConsumerModel.this.consumerId = consumerId;
            return this;
        }

        public ConsumerModel.Builder setConsumerName(String consumerName) {
            ConsumerModel.this.consumerName = consumerName;
            return this;
        }

        public ConsumerModel.Builder setTownId(int townId) {
            ConsumerModel.this.townId = townId;
            return this;
        }

        public ConsumerModel.Builder setPurchaseModels(List<PurchaseModel> purchaseModels) {
            ConsumerModel.this.purchaseModels = purchaseModels;
            return this;
        }

        public ConsumerModel build() {
            return ConsumerModel.this;
        }
    }
}
