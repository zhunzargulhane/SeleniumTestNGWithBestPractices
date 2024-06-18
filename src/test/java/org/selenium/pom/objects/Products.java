package org.selenium.pom.objects;

import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;

public class Products {
    private int id;
    private String name;

    private boolean featured;

    public Products() {
    }

    public Products(int id) throws IOException {
        Products[] products = JacksonUtils.deserializeJson("products.json", Products[].class);
        for (Products product : products) {
            if (product.getId() == id) {
                this.id = product.getId();
                this.name = product.getName();
            }
        }
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
