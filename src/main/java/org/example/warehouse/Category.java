package org.example.warehouse;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
