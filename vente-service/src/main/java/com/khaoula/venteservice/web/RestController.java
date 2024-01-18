package com.khaoula.venteservice.web;

import com.khaoula.venteservice.Modele.Acheteur;
import com.khaoula.venteservice.Modele.Produit;
import com.khaoula.venteservice.acheteurs.AcheteurFeignController;
import com.khaoula.venteservice.config.GlobalConfig;
import com.khaoula.venteservice.config.VenteConfig;
import com.khaoula.venteservice.entities.Vente;
import com.khaoula.venteservice.produits.ProduitFeignController;
import com.khaoula.venteservice.repositories.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")

public class RestController {

    @Autowired
    VenteRepository venteRepository;

    @Autowired
    private ProduitFeignController produitFeignController;

    @Autowired
    private AcheteurFeignController acheteurFeignController;

    @Autowired
    GlobalConfig globalConfig;

    @Autowired
    VenteConfig venteConfig;

    @GetMapping("/globalConfig")
    public GlobalConfig globalConfig(){ return globalConfig;}

    @GetMapping("/venteConfig")
    public  VenteConfig venteConfig(){ return venteConfig;}

    @GetMapping("/ventes")
    public List<Vente> getAll(){
        List<Vente> lv = venteRepository.findAll();

        List<Produit> lp = produitFeignController.findAll();

        List<Acheteur> la = acheteurFeignController.findAll();

        for (Vente v:lv){
            for (Produit p:lp){
                if (v.getIdP() == p.getIdP()){
                    v.setProduit(p); break;
                }
            }
        }

        for (Vente v:lv){
            for (Acheteur a:la){
                if (v.getIdA() == a.getIdA()){
                    v.setAcheteur(a); break;
                }
            }
        }

        return lv;
    }

    @GetMapping("/ventes/{id}")
    public Vente getById(@PathVariable Long id){
        Vente v = venteRepository.findById(id).get();

        Acheteur a = acheteurFeignController.findBy(v.getIdA());
        v.setAcheteur(a);

        Produit p = produitFeignController.findBy(v.getIdP());
        v.setProduit(p);

        return v;
    }

    @PostMapping("/ventes")
    public Vente addVente(@RequestBody Vente vente){
        Vente v1 = venteRepository.save(vente);
        Vente v = venteRepository.findById(v1.getIdVente()).get();

        Acheteur a = acheteurFeignController.findBy(v.getIdA());
        v.setAcheteur(a);

        Produit p = produitFeignController.findBy(v.getIdP());
        v.setProduit(p);

        return v;
    }

    @PutMapping("/ventes/{id}")
    public void updateVente(@PathVariable Long id, @RequestBody Vente v){
        Vente vente = venteRepository.findById(id).get();

        if (v.getIdA() != null) {vente.setIdA(v.getIdA());}
        if (v.getIdP() != null) {vente.setIdP(v.getIdP());}
        if (v.getAcheteur() != null) {vente.setAcheteur(v.getAcheteur());}
        else {
            Acheteur acheteur = acheteurFeignController.findBy(v.getIdA());
            vente.setAcheteur(acheteur);
        }
        if (v.getProduit() != null) {vente.setProduit(v.getProduit());}
        else {
            Produit produit = produitFeignController.findBy(v.getIdP());
            vente.setProduit(produit);
        }

        venteRepository.save(vente);
    }

    @DeleteMapping("/ventes/{id}")
    public void supprimer(@PathVariable Long id){
        venteRepository.deleteById(id);
    }

}
