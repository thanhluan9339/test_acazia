package com.test.repository;

import com.test.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(value =
            "select * from product p where (:name is null or p.name like %:name%)"
                    + " and (:category_tag is null or p.category_tag=:category_tag) ", nativeQuery = true)
    Page<Product> getAllByCondition(@Param("name") String name , @Param("category_tag") String categoryTag
            , Pageable pageable);

    boolean existsByName(String name);

    @Query(value =
            "SELECT * FROM product p LEFT JOIN category c on p.category_tag=c.tag " +
                    " WHERE c.name like %:category_name% " +
                    " ORDER BY p.price DESC , p.name", nativeQuery = true)
    Page<Product> getAllByConditionTest(@Param("category_name") String categoryName ,Pageable pageable);
}
