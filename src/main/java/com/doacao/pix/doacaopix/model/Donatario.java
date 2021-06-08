package com.doacao.pix.doacaopix.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "TB_DONATARIO")
public class Donatario extends Pessoa {

    private static final long serialVersionUID = -9054989749142431146L;

    @Getter
    @Setter
    @Column(name = "CHAVE_PIX")
    private String chavePix;

}
