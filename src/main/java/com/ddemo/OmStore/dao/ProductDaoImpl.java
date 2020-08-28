package com.ddemo.OmStore.dao;

import com.ddemo.OmStore.entity.Product;

public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao{
    
    public ProductDaoImpl(Class<Product> entityClass) {
        super(entityClass);
    }

   
}
