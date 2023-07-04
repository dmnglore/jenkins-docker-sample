package com.demo.devops.jenkinsdockersample.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.devops.jenkinsdockersample.entity.Product;
import com.demo.devops.jenkinsdockersample.service.IProductService;
@RestController
public class ProductController 
{
@Autowired
private IProductService productService;
//rest api
@GetMapping(value = "/product")
public List<Product> getProduct() 
{
List<Product> products = productService.findAll();
return products;
}
}

