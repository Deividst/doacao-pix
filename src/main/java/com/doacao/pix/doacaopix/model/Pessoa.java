package com.doacao.pix.doacaopix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1101537401328672138L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CODIGO")
    private Long codigo;

    @Column(name = "NOME")
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ENDERECO")
    private String endereco;

    protected Pessoa(CustomBuilder<?> builder) {
        this.codigo = builder.codigo;
        this.nome = builder.nome;
        this.cpfCnpj = builder.cpfCnpj;
        this.usuario = builder.usuario;
        this.email = builder.email;
        this.endereco = builder.endereco;
    }

    public static CustomBuilder builder() {
        return new CustomBuilder() {
            @Override
            public CustomBuilder getThis() {
                return this;
            }
        };
    }

    public abstract static class CustomBuilder<T extends CustomBuilder<T>> {
        private Long codigo;
        private String nome;
        private String cpfCnpj;
        private Usuario usuario;
        private String email;
        private String endereco;

        public abstract T getThis();

        public T codigo(Long codigo) {
            this.codigo = codigo;
            return this.getThis();
        }

        public T nome(String nome) {
            this.nome = nome;
            return this.getThis();
        }

        public T cpfCnpj(String cpfCnpj) {
            this.cpfCnpj = cpfCnpj;
            return this.getThis();
        }

        public T usuario(Usuario usuario) {
            this.usuario = usuario;
            return this.getThis();
        }

        public T email(String email) {
            this.email = email;
            return this.getThis();
        }

        public T endereco(String endereco) {
            this.endereco = endereco;
            return this.getThis();
        }


        public Pessoa build() {
            return new Pessoa(this);
        }
    }

}
