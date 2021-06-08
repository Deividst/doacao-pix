package com.doacao.pix.doacaopix.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "TB_DOADOR")
public class Doador extends Pessoa {

    private static final long serialVersionUID = 7834350020257751049L;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CODIGO_DOADOR", referencedColumnName = "CODIGO")
    private List<Doacao> doacaoList;

}
