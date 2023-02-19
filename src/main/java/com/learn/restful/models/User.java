package com.learn.restful.models;

import lombok.*;

import java.util.Date;

@ToString
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Date birthDate;
}
