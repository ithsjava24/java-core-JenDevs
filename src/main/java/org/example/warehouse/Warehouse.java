package org.example.warehouse;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


public class Warehouse {

    private String name;
    private static Warehouse instance;
    private final List<ProductRecord> products = new ArrayList<>();
    private final List<ProductRecord> changedProducts = new ArrayList<>();

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

    public List<ProductRecord> getChangedProducts() {
        if(changedProducts.isEmpty()) {
                return Collections.emptyList();
            }
        return List.copyOf(changedProducts);
    }

    public void updateProductPrice(UUID uuid, BigDecimal price) {
        boolean productFound = false;
        ProductRecord oldProduct;

        for(ProductRecord product : products) {
            if(uuid.equals(product.uuid())) {
                if(!product.price().equals(price)) {
                    oldProduct = product;
                    ProductRecord updatedProduct = new ProductRecord(product.uuid(), product.category(), price, product.productName());

                    products.remove(product);
                    products.add(updatedProduct);

                    changedProducts.removeIf(p -> p.uuid().equals(uuid));
                    changedProducts.add(oldProduct);
                }
                productFound = true;
                break;
            }
       }

        if(!productFound) {
            throw new IllegalArgumentException("Product with that id doesn't exist.");
        }
    }

    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        return products.stream()
                .collect(Collectors.groupingBy(ProductRecord::category, Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList)));
    }

    public List<ProductRecord> getProductsBy(Category category)  {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .toList();

    }

    public Optional<ProductRecord> getProductById(UUID uuid) {

        return products.stream()
                .filter(product -> product.uuid().equals(uuid))
                .findFirst();
    }
}












