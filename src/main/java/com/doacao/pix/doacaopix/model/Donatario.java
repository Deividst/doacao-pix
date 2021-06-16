package com.doacao.pix.doacaopix.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_DONATARIO")
public class Donatario extends Pessoa {

    private static final long serialVersionUID = -9054989749142431146L;

    @Getter
    @Setter
    @Column(name = "CHAVE_PIX")
    private String chavePix;

    public Donatario(CustomBuilder builder) {
        super(builder);
        this.chavePix = builder.chavePix;
    }

    public static CustomBuilder builder() {
        return new CustomBuilder();
    }

    public static class CustomBuilder extends Pessoa.CustomBuilder<CustomBuilder> {

        private String chavePix;

        @Override
        public CustomBuilder getThis() {
            return this;
        }

        public CustomBuilder chavePix(String chavePix) {
            this.chavePix = chavePix;
            return this;
        }

        public Donatario build() {
            return new Donatario(this);
        }

    }
}
