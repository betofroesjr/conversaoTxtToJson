package com.betofroes.desafiotecnicobetofroes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TxtToJsonDto {

    @JsonProperty(value = "id_usuário")
    private Integer id;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "id_pedido")
    private Integer idPedido;

    @JsonProperty(value = "id_produto")
    private Integer idProduto;

    @JsonProperty(value = "valor_do_produto")
    private String valor;

    @JsonProperty(value = "data_compra")
    private String dataCompra;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }


}
