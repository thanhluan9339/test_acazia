package com.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "category")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @Column(name = "tag", unique = true, nullable = false)
    private String tag;

    @Column(name = "name", columnDefinition = "nvarchar", nullable = false)
    private String name;


}
