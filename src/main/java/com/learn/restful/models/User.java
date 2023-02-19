package com.learn.restful.models;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends RepresentationModel<User> {
    private Integer id;
    @Size(min = 2, message = "Name must have 2 characters at least")
    private String name;
    @Past
    private Date birthDate;
}
