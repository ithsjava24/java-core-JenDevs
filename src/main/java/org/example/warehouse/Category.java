package org.example.warehouse;

public class Category {

    private String name;

    private Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category of(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        return new Category(name);
    }
}
