package com.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "product")
@Entity
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "category_tag", nullable = false)
    private String categoryTag;

    @Column(name = "price", nullable = false)
    private Double price = Double.NaN;
}
