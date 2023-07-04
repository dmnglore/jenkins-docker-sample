package com.demo.devops.jenkinsdockersample.service;
import java.util.List;
import com.demo.devops.jenkinsdockersample.entity.Product;

public interface IProductService 
{
List<Product> findAll();
}
