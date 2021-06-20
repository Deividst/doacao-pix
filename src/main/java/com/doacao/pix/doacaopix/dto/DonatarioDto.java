package com.doacao.pix.doacaopix.dto;

import com.doacao.pix.doacaopix.enums.TipoUsuario;
import com.doacao.pix.doacaopix.model.Doacao;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DonatarioDto implements Serializable {

    private static final long serialVersionUID = 7585345037574905082L;

    private Long codigo;

    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @NotBlank(message = "O campo cpfCnpj é obrigatório")
    private String cpfCnpj;

    @NotBlank(message = "O campo usuario é obrigatório")
    private String usuario;

    @NotBlank(message = "O campo senha é obrigatório")
    private String senha;
    private Long codigoUsuario;
    private String email;

    @NotNull(message = "O campo senha é obrigatório")
    private TipoUsuario tipo;
    private String endereco;

    @NotBlank(message = "O campo chavePix é obrigatório")
    private String chavePix;

}
