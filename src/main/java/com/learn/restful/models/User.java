package com.learn.restful.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_table")
public class User extends RepresentationModel<User> implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, message = "Name must have 2 characters at least")
    private String name;
//    @Past
//    private Date birthDate;


}
