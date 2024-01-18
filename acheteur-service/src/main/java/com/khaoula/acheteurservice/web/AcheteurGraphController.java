package com.khaoula.acheteurservice.web;

import com.khaoula.acheteurservice.entities.Acheteur;
import com.khaoula.acheteurservice.repositories.AcheteurRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AcheteurGraphController {

    @Autowired
    AcheteurRepository acheteurRepository;

    @QueryMapping
    public List<Acheteur> findAllAcheteurs(){
        return acheteurRepository.findAll();
    }

    @QueryMapping
    public Acheteur findAcheteurById(@Argument Integer id){
        return acheteurRepository.findById(id).get();
    }

    @MutationMapping
    public Acheteur createAcheteur(@Argument Acheteur acheteur){
        Acheteur a = new Acheteur();
        BeanUtils.copyProperties(acheteur,a);
        return acheteurRepository.save(a);
    }

    @MutationMapping
    public void updateAcheteur(@Argument(name = "id") Integer id, @Argument(name = "input") Acheteur acheteur){
        Acheteur ar = acheteurRepository.findById(id).get();

        if(acheteur.getNom()!=null) {ar.setNom(acheteur.getNom());}
        if(acheteur.getVille()!=null){ar.setVille(acheteur.getVille());}

        acheteurRepository.save(ar);
    }

    @MutationMapping
    public void deleteAcheteur(@Argument Integer id){
        acheteurRepository.deleteById(id);
    }
}
