package model;

public class ProductModel {
    private int productId;
    private String productName;

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    private ProductModel() {
    }

    public static ProductModel.Builder builder() {
        return new ProductModel().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public ProductModel.Builder setProductId(int productId) {
            ProductModel.this.productId = productId;
            return this;
        }

        public ProductModel.Builder setProductName(String productName) {
            ProductModel.this.productName = productName;
            return this;
        }

        public ProductModel build() {
            return ProductModel.this;
        }
    }
}
