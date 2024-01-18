package com.khaoula.venteservice.produits;

import com.khaoula.venteservice.Modele.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "PRODUIT-SERVICE")


public interface ProduitFeignController {
    @GetMapping("/api/produits")
    public List<Produit> findAll();

    @GetMapping("/api/produits/{id}")
    public Produit findBy(@PathVariable Long id);
}
