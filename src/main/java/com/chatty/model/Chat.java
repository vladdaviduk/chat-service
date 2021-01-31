package com.chatty.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String firstOwner;

    @Column
    private String secondOwner;

    @OneToMany(mappedBy="chat")
    private List<Message> messages = new ArrayList<>();

    public Chat(String firstOwner, String secondOwner) {
        this.firstOwner = firstOwner;
        this.secondOwner = secondOwner;
    }
}
