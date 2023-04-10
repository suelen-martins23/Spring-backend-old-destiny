package com.gamecms.olddestiny.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GenericReturn {
    public String descricao;
    public boolean isSucess;
}
