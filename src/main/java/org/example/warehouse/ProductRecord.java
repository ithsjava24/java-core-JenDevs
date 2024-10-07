package org.example.warehouse;

// UUID, name, Category
// En klass som har en konstruktor som sätter fälten, getter och setter finns här i record, hashCode,

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRecord (UUID uuid, Category category, BigDecimal price, String productName) {

}
