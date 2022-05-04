package com.cocus.bahamasapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long invoiceId;

    @NotNull
    @Max(9)
    private Integer fiscalId;

    @NotNull
    @Max(70)
    private String name;

    @NotNull
    @Max(320)
    private String email;

    public ClientDTO(Integer fiscalId, String name, String email) {
        this.fiscalId = fiscalId;
        this.name = name;
        this.email = email;
    }
}
