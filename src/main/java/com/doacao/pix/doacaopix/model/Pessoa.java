package com.doacao.pix.doacaopix.model;

import com.doacao.pix.doacaopix.enums.TipoUsuario;
import lombok.*;
import lombok.experimental.SuperBuilder;

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

    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

    @Column(name = "NOME_USUARIO")
    private String usuario;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "EMAIL")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_USUARIO")
    private TipoUsuario tipo;

    @Column(name = "ENDERECO")
    private String endereco;

    protected Pessoa(CustomBuilder<?> builder) {
        this.codigo = builder.codigo;
        this.nome = builder.nome;
        this.cpfCnpj = builder.cpfCnpj;
        this.usuario = builder.usuario;
        this.senha = builder.senha;
        this.email = builder.email;
        this.tipo = builder.tipo;
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
        private String usuario;
        private String senha;
        private String email;
        private TipoUsuario tipo;
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

        public T usuario(String usuario) {
            this.usuario = usuario;
            return this.getThis();
        }

        public T senha(String senha) {
            this.senha = senha;
            return this.getThis();
        }

        public T email(String email) {
            this.email = email;
            return this.getThis();
        }

        public T tipo(TipoUsuario tipo) {
            this.tipo = tipo;
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
