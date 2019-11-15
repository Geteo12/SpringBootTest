package com.example.demo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dao.ProductDAO;
import com.exception.produitIntrouvableException;
import com.model.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "gestion des produits")
@RestController
public class ProductController {

	@Autowired
	private ProductDAO productDAO;

	
	//produits	
	@ApiOperation(value = "Récupère tout les produits")
	@GetMapping(value = "produits")		
	public List<Product> listeProduits (){
		return productDAO.findAll();
	}
	@ApiOperation(value = "Récupère les produits au dessus d'un certains montant")
	@GetMapping(value = "produits/prix/{prixLimit}")
	public List<Product> listeProduitsMoinsCher (@PathVariable int prixLimit){
		return productDAO.chercherUnProduitCher(prixLimit);	
	}
	@ApiOperation(value = "Récupère le produit qui a le nom transmis")
	@GetMapping(value = "produits/nom/noms")
	public List<Product> listeNomsProduits (){
		return productDAO.nomsProduits();
	}
	
	@ApiOperation(value = "Récupère le produit grace a son ID")
	@GetMapping(value = "produits/{id}")
	public Product afficherUnProduit (@PathVariable int id) throws produitIntrouvableException {

		Product product = productDAO.findById(id);	
		
		if(product == null) {
			throw new produitIntrouvableException ("le produit avec l'id "+ id +" n'existe pas");
		}
		
		return product;
	}
	
	@PostMapping(value = "produits")
	public void saveProduct (@RequestBody Product product) {

		productDAO.save(product);
	}
}
