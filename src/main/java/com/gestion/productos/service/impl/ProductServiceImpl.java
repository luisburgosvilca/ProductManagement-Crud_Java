package com.gestion.productos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.productos.exception.ResourceNotFoundException;
import com.gestion.productos.model.ProductEntity;
import com.gestion.productos.repository.ProductRepository;
import com.gestion.productos.response.ProductResponse;
import com.gestion.productos.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<ProductResponse> productsList(){
		
		List<ProductEntity> productList  = productRepository.findAll();
		
		List<ProductResponse> productResponseList = new ArrayList<ProductResponse>();
		
		for(ProductEntity productEntity: productList) {
			
			ProductResponse productResponse = new ProductResponse();
			
			BeanUtils.copyProperties(productEntity, productResponse);
			
			productResponseList.add(productResponse);
		}

		
		return productResponseList;
		
	}
	
	@Override
	public void productSave(ProductEntity productEntity) {
		productRepository.save(productEntity);
	}
	
	@Override
	public ProductResponse findById(Integer id) {
		
		Optional<ProductEntity> productEntity = productRepository.findById(id);
		
		if(!productEntity.isPresent()) {
			throw new ResourceNotFoundException("Producto no encotrado, con ID: "+id);
		}

		ProductResponse productResponse = new ProductResponse();
		
		BeanUtils.copyProperties(productEntity.get(), productResponse);
		
		return productResponse;

	}  
	
	@Override
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}

}
