package com.doacao.pix.doacaopix.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_OPERADOR")
public class Operador extends Pessoa {

    private static final long serialVersionUID = 3530126406011912497L;

    @Getter
    @Setter
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "ULTIMO_ACESSO")
    private LocalDateTime ultimoAcesso;

    public Operador(Operador.CustomBuilder builder) {
        super(builder);
        this.ultimoAcesso = builder.ultimoAcesso;
    }

    public static Operador.CustomBuilder builder() {
        return new Operador.CustomBuilder();
    }

    public static class CustomBuilder extends Pessoa.CustomBuilder<Operador.CustomBuilder> {

        private LocalDateTime ultimoAcesso;

        @Override
        public Operador.CustomBuilder getThis() {
            return this;
        }

        public Operador.CustomBuilder ultimoAcesso(LocalDateTime ultimoAcesso) {
            this.ultimoAcesso = ultimoAcesso;
            return this;
        }

        public Operador build() {
            return new Operador(this);
        }

    }

}
