package com.slc.design.creational.builder.variety;

import lombok.ToString;

/**
 * @author sulin
 */
@ToString
public class Product {

    private String name;
    private String password;
    private String id;

    public Product(ProductBuilder productBuilder) {
        this.name= productBuilder.name;
        this.password= productBuilder.password;
        this.id= productBuilder.id;
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {

        private String name;
        private String password;
        private String id;

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder password(String password) {
            this.password = password;
            return this;
        }

        public ProductBuilder id(String id) {
            this.id = id;
            return this;
        }

        public Product build(){
            return new Product(this);
        }
    }
}
