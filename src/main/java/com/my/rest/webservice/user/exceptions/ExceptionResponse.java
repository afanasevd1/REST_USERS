
package com.my.rest.webservice.user.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private Date timeStamp;
    private String error;
    private String errorDescription;
}
