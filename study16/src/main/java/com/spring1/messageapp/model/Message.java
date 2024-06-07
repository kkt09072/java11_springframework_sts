package com.spring1.messageapp.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Long id;
    private String sender;
    private String receiver;
    private String subject;
    private String body;
    private Date timestamp;
}