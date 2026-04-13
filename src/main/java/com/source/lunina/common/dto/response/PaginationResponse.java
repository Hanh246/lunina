package com.source.lunina.common.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.source.lunina.common.dto.pagination.PaginationMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaginationResponse<T> extends BaseResponse<T>{
    @JsonProperty("metadata")
    private PaginationMetadata metadata;
}
