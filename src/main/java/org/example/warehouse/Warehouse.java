package org.example.warehouse;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;

public class Warehouse {

    private String name;
    private static Warehouse instance;
    private List<ProductRecord> products = new ArrayList<>();
    private List<ProductRecord> changedProducts = new ArrayList<>();


    private Warehouse(String name) {
        this.name = name;

    }

    public static Warehouse getInstance() {
        return new Warehouse("MyStore");
    }

    public static Warehouse getInstance(String name) {
        if(instance == null) {
            instance = new Warehouse(name);
        }

        return instance;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public List<ProductRecord> getProducts() {
        if(products.isEmpty()) {
            return Collections.emptyList();
        }
        return List.copyOf(products);
    }

    // todo: find multiple products from same category
    public ProductRecord addProduct(UUID uuid, String name, Category category, BigDecimal price)   {
        for(ProductRecord product : products) {
            if(product.uuid().equals(uuid)) {
                throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
            }
        }

        if(uuid == null ) {
            uuid = UUID.randomUUID();
        }

        if ( name == null || name.isEmpty() ) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }

        if(category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }

        if(price == null) {
            price = BigDecimal.ZERO;
        }

        ProductRecord newProduct = new ProductRecord(uuid, category, price, name);
        products.add(newProduct);

        return newProduct;
    }

    // todo: find changed products is empty
    public List<ProductRecord> getChangedProducts() {
//        if(changedProducts.isEmpty()) {
//            throw new IllegalArgumentException("Product list is empty");
//        }
        return Collections.singletonList(changedProducts.getFirst());
    }

    // todo: find changed products returns product
    // todo: throws IllegalArgumentException when trying to change an invalid id
    // todo: changing a products price should be saved
    public void updateProductPrice(UUID uuid, BigDecimal bigDecimal) {

    }

    // todo: group them by category
    public List<ProductRecord> getProductsGroupedByCategories() {

        return List.copyOf(changedProducts);
    }

    // todo: find all products belonging to a category
    public List<ProductRecord> getProductsBy(Category category)  {
        return List.copyOf(products);

    }

    public Optional<ProductRecord> getProductById(UUID uuid) {
        if(products.isEmpty())
            throw new RuntimeException("Product list is empty");

        for(ProductRecord product : products) {
            if(product.uuid().equals(uuid)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

}












