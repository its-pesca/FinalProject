package com.khaoula.acheteurservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcheteurEvent {

    private String message;
    private String statute;
    private Acheteur acheteur;

}
