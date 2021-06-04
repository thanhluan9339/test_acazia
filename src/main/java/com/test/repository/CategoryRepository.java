package com.test.repository;

import com.test.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query(value =
            "select * from category ca where (:name is null or ca.name like %:name%)"
                    + " and (:tag is null or ca.tag=:tag) ", nativeQuery = true)
    Page<Category> getAllByCondition(@Param("name") String name , @Param("tag") String tag , Pageable pageable);

    boolean existsByTag(String tag);
}
