package com.chatty.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String owner;

    @Column
    private Date timestamp;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name="chat_id", nullable=false)
    private Chat chat;
}
