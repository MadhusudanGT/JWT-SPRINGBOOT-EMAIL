package com.javatechie.jwt.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MAIL_TBL")
public class EmailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;

    private String email;

    private String subject;

    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;

}