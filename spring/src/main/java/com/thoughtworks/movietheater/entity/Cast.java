package com.thoughtworks.movietheater.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("cast")
public class Cast {
    @Id
    private int id;
    @Column("name")
    private String name;
    @Column("avatar")
    private String avatar;

    public Cast(int id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }
}
