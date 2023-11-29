package com.gestion.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.productos.model.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{

}
