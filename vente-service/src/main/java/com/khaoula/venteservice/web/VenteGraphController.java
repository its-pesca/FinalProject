package com.khaoula.venteservice.web;

import com.khaoula.venteservice.Modele.Acheteur;
import com.khaoula.venteservice.Modele.Produit;
import com.khaoula.venteservice.acheteurs.AcheteurFeignController;
import com.khaoula.venteservice.entities.Vente;
import com.khaoula.venteservice.produits.ProduitFeignController;
import com.khaoula.venteservice.repositories.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VenteGraphController {

    @Autowired
    VenteRepository venteRepository;

    @Autowired
    AcheteurFeignController acheteurFeignController;

    @Autowired
    ProduitFeignController produitFeignController;

    @QueryMapping
    public List<Vente> findAllVentes(){
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

    @QueryMapping
    public Vente findVenteById(@Argument Long id){
        Vente v = venteRepository.findById(id).get();

        Acheteur a = acheteurFeignController.findBy(v.getIdA());
        v.setAcheteur(a);

        Produit p = produitFeignController.findBy(v.getIdP());
        v.setProduit(p);

        return v;
    }

    @MutationMapping
    public Vente createVente(@Argument Vente vente){
        Vente v1 = venteRepository.save(vente);
        Vente v = venteRepository.findById(v1.getIdVente()).get();

        Acheteur a = acheteurFeignController.findBy(v.getIdA());
        v.setAcheteur(a);

        Produit p = produitFeignController.findBy(v.getIdP());
        v.setProduit(p);

        return v;
    }

    @MutationMapping
    public void updateVente(@Argument(name = "id") Long id,@Argument(name = "input") Vente v){
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

    @MutationMapping
    public void deleteVente(@Argument Long id){
        venteRepository.deleteById(id);
    }
}
