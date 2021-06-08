package com.doacao.pix.doacaopix.dto;

import com.doacao.pix.doacaopix.enums.TipoUsuario;
import com.doacao.pix.doacaopix.model.Doacao;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DoadorDto implements Serializable {

    private static final long serialVersionUID = 7585345037574905082L;

    private Long codigo;
    private String nome;
    private String cpfCnpj;
    private String usuario;
    private String senha;
    private String email;
    private TipoUsuario tipo;
    private String endereco;
    private List<DoacaoDto> doacaoList;

}
