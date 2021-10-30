package model;

import java.util.ArrayList;
import java.util.List;

public class TownModel {
    private int townId;
    private String townName;
    private List<ProviderModel> providerModels;
    private List<ConsumerModel> consumerModels;

    public int getTownId() {
        return townId;
    }

    public String getTownName() {
        return townName;
    }

    public List<ProviderModel> getProviderModels() {
        return new ArrayList<>(providerModels);
    }

    public List<ConsumerModel> getConsumerModels() {
        return new ArrayList<>(consumerModels);
    }

    private TownModel() {
    }

    public static TownModel.Builder builder() {
        return new TownModel().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public TownModel.Builder setTownId(int townId) {
            TownModel.this.townId = townId;
            return this;
        }

        public TownModel.Builder setTownName(String townName) {
            TownModel.this.townName = townName;
            return this;
        }

        public TownModel.Builder setProviderModels(List<ProviderModel> providerModels) {
            TownModel.this.providerModels = providerModels;
            return this;
        }

        public TownModel.Builder setConsumerModels(List<ConsumerModel> consumerModels) {
            TownModel.this.consumerModels = consumerModels;
            return this;
        }

        public TownModel build() {
            return TownModel.this;
        }
    }
}
