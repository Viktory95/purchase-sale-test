package model;

public class ResultModel {
    private String townName;
    private String productName;
    private String providerName;
    private String consumerName;

    public String getTownName() {
        return townName;
    }

    public String getProductName() {
        return productName;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getConsumerName() {
        return consumerName;
    }

    private ResultModel() {
    }

    public static Builder builder() {
        return new ResultModel().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setTownName(String townName) {
            ResultModel.this.townName = townName;
            return this;
        }

        public Builder setProductName(String productName) {
            ResultModel.this.productName = productName;
            return this;
        }

        public Builder setProviderName(String providerName) {
            ResultModel.this.providerName = providerName;
            return this;
        }

        public Builder setConsumerName(String consumerName) {
            ResultModel.this.consumerName = consumerName;
            return this;
        }

        public ResultModel build() {
            return ResultModel.this;
        }
    }
}