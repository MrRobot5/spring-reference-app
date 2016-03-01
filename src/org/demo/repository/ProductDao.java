package org.demo.repository;

import java.util.List;

import org.demo.domain.Product;

public interface ProductDao {

    public List<Product> getProductList();

    public void saveProduct(Product prod);

}
