package model;

import java.util.ArrayList;
import java.util.List;

public class ProviderModel {
    private int providerId;
    private String providerName;
    private int townId;
    private List<SellingModel> sellingModels;

    public int getProviderId() {
        return providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public int getTownId() {
        return townId;
    }

    public List<SellingModel> getSellingModels() {
        return new ArrayList<>(sellingModels);
    }

    private ProviderModel() {
    }

    public static ProviderModel.Builder builder() {
        return new ProviderModel().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public ProviderModel.Builder setProviderId(int providerId) {
            ProviderModel.this.providerId = providerId;
            return this;
        }

        public ProviderModel.Builder setProviderName(String providerName) {
            ProviderModel.this.providerName = providerName;
            return this;
        }

        public ProviderModel.Builder setTownId(int townId) {
            ProviderModel.this.townId = townId;
            return this;
        }

        public ProviderModel.Builder setSellingModels(List<SellingModel> sellingModels) {
            ProviderModel.this.sellingModels = sellingModels;
            return this;
        }

        public ProviderModel build() {
            return ProviderModel.this;
        }
    }
}
