package com.meow.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.meow.Utils.Enums;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Data
public class ResponseObject<T>
{
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Enums.ResponseStatus status = Enums.ResponseStatus.SUCCESS;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
