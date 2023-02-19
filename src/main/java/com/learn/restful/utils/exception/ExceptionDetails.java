package com.learn.restful.utils.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExceptionDetails {
    private Date timestamp;
    private String status;
    private String message;
    private String details;

}
