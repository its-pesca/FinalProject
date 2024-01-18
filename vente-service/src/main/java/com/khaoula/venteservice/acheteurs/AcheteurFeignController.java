package com.khaoula.venteservice.acheteurs;

import com.khaoula.venteservice.Modele.Acheteur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "ACHETEUR-SERVICE")

public interface AcheteurFeignController {

    @GetMapping("/api/acheteurs")
    public List<Acheteur> findAll();

    @GetMapping("/api/acheteurs/{id}")
    public Acheteur findBy(@PathVariable Long id);
}
