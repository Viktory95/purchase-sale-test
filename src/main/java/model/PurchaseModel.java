package model;

public class PurchaseModel {
    private int purchaseId;
    private int productId;
    private int consumerId;

    public int getPurchaseId() {
        return purchaseId;
    }

    public int getProductId() {
        return productId;
    }

    public int getConsumerId(){
        return consumerId;
    }

    private PurchaseModel() {
    }

    public static PurchaseModel.Builder builder() {
        return new PurchaseModel().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public PurchaseModel.Builder setPurchaseId(int purchaseId) {
            PurchaseModel.this.purchaseId = purchaseId;
            return this;
        }

        public PurchaseModel.Builder setProductId(int productId) {
            PurchaseModel.this.productId = productId;
            return this;
        }

        public PurchaseModel.Builder setConsumerId(int consumerId) {
            PurchaseModel.this.consumerId = consumerId;
            return this;
        }

        public PurchaseModel build() {
            return PurchaseModel.this;
        }
    }
}
