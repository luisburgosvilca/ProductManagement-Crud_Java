package com.gestion.productos.service;

import java.util.List;

import com.gestion.productos.model.ProductEntity;
import com.gestion.productos.response.ProductResponse;

public interface ProductService {
	
	List<ProductResponse> productsList();
	
	void productSave(ProductEntity productEntity);
	
	ProductResponse findById(Integer id);
	
	void deleteProduct(Integer id);

}
