package com.khaoula.produitservice.web;

import com.khaoula.produitservice.entities.Produit;
import com.khaoula.produitservice.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProduitGraphController {

    @Autowired
    ProduitRepository produitRepository;

    @QueryMapping
    public List<Produit> findAllProduits(){
        return produitRepository.findAll();
    }

    @QueryMapping
    public Produit findProduitById(@Argument Integer id){
        return produitRepository.findById(id).get();
    }

    @MutationMapping
    public Produit createProduit(@Argument Produit produit){
        return produitRepository.save(produit);
    }

    @MutationMapping
    public void updateProduit(@Argument(name = "id") Integer id,@Argument(name = "input") Produit produit){
        Produit pr = produitRepository.findById(id).get();

        if (produit.getMarque()!=null){pr.setMarque(produit.getMarque());}
        if (produit.getDesc()!=null){pr.setDesc(produit.getDesc());}
        if (produit.getPrix()!=null){pr.setPrix(produit.getPrix());}
        if (produit.getQuantite()!=null){pr.setQuantite(produit.getQuantite());}

        produitRepository.save(pr);
    }

    @MutationMapping
    public void deleteProduit(@Argument Integer id){
        produitRepository.deleteById(id);
    }
}
