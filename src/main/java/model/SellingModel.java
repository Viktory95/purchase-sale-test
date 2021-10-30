package model;

public class SellingModel {
    private int sellingId;
    private int productId;
    private int providerId;

    public int getSellingId() {
        return sellingId;
    }

    public int getProductId() {
        return productId;
    }

    public int getProviderId() {
        return providerId;
    }

    private SellingModel() {
    }

    public static SellingModel.Builder builder() {
        return new SellingModel().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public SellingModel.Builder setSellingId(int sellingId) {
            SellingModel.this.sellingId = sellingId;
            return this;
        }

        public SellingModel.Builder setProductId(int productId) {
            SellingModel.this.productId = productId;
            return this;
        }

        public SellingModel.Builder setProviderId(int providerId) {
            SellingModel.this.providerId = providerId;
            return this;
        }

        public SellingModel build() {
            return SellingModel.this;
        }
    }
}
