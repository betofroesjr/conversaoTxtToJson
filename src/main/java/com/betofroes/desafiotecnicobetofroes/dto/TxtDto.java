package com.betofroes.desafiotecnicobetofroes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TxtDto implements Comparable<TxtDto> {

    private Integer order;

    @JsonProperty(value = "id_field")
    private String idField;
    private Integer size;

    public TxtDto() {
    }

    public TxtDto(Integer order, String idField, Integer size) {
        this.order = order;
        this.idField = idField;
        this.size = size;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public int compareTo(TxtDto o) {
        if(this.getOrder() > o.getOrder()) {
            return 1;
        } else if(this.getOrder() < o.getOrder()) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TxtDto txtDto = (TxtDto) o;
        return Objects.equals(idField, txtDto.idField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idField);
    }
}
