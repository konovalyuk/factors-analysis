package com.intapp.platform.factorial.exception;

import lombok.*;

import java.io.Serializable;

/**
 * Created by maksymk on 4/24/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GlobalErrorInfo implements Serializable {

    public String url;
    public String message;
    public StackTraceElement[] stackTraceElements;

}
