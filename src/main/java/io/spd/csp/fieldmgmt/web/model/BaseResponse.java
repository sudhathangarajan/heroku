package io.spd.csp.fieldmgmt.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class BaseResponse<T> {

    private String code;

    private Status status;

    private T data;

    public enum Status {

        success,

        failed;
    }
}
