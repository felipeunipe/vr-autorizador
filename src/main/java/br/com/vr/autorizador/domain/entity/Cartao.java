package br.com.vr.autorizador.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "cartao")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cartao {

    @Id
    private String id;
    @Indexed(unique = true)
    private String numeroCartao;
    @Indexed(unique = true)
    private String senha;
    private BigDecimal saldo;
    @CreatedDate
    private LocalDateTime dhCriacao;
    @LastModifiedDate
    private LocalDateTime dhAtualizacao;
    @Version
    private Long version;

}
