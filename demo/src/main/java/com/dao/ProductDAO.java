package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

	List<Product> 	findAll();
	Product 		findById(int id);
	Product			save(Product product);
	
	@Query("SELECT new Product(p.id, p.nom, p.prix) FROM Product p WHERE p.prix > :prixLimit")
	List<Product>  chercherUnProduitCher(@Param("prixLimit") int prix);
	
	@Query("select new Product(p.nom) FROM Product p")
	List<Product> nomsProduits ();
	
	
	
}
