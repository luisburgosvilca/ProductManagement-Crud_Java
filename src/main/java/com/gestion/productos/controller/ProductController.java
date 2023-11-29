package com.gestion.productos.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.productos.model.ProductEntity;
import com.gestion.productos.response.ProductResponse;
import com.gestion.productos.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<ProductResponse> findByAll(){
		return productService.productsList();
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> findById(@PathVariable(name = "id") Integer id){
		
		try {
			ProductResponse productResponse = productService.findById(id);
			return new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK);	
			
		} catch (Exception e) {
			return new ResponseEntity<ProductResponse>(HttpStatus.NOT_FOUND);
			
		}
	}
	

	@PostMapping
	public void saveProduct(@RequestBody ProductEntity productEntity) {
		productService.productSave(productEntity);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody ProductEntity productEntityRequest, @PathVariable Integer id){
		
		try {
			ProductResponse productResponse =  productService.findById(id);	
			
			productResponse.setName(productEntityRequest.getName());
			productResponse.setPrice(productEntityRequest.getPrice());
			
			BeanUtils.copyProperties(productResponse, productEntityRequest);
			
			productService.productSave(productEntityRequest);
			
			return new ResponseEntity<ProductResponse>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ProductResponse>(HttpStatus.NOT_FOUND);
		}

	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
	}
	
}
