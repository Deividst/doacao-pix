package com.doacao.pix.doacaopix.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_DOADOR")
public class Doador extends Pessoa {

    private static final long serialVersionUID = 7834350020257751049L;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CODIGO_DOADOR", referencedColumnName = "CODIGO")
    private List<Doacao> doacaoList;

    public Doador(CustomBuilder builder) {
        super(builder);
        this.doacaoList = builder.doacaoList;
    }

    public static CustomBuilder builder() {
        return new CustomBuilder();
    }

    public static class CustomBuilder extends Pessoa.CustomBuilder<CustomBuilder> {

        private List<Doacao> doacaoList;

        @Override
        public Doador.CustomBuilder getThis() {
            return this;
        }

        public Doador.CustomBuilder doacaoList(List<Doacao> doacaoList) {
            this.doacaoList = doacaoList;
            return this;
        }

        public Doador build() {
            return new Doador(this);
        }

    }

}
