package com.example.backend.technology;

import com.example.backend.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long>
{
    @Query("SELECT tt.tag FROM TechnologyTags tt WHERE tt.technology.id = :techId")
    List<Tag> findTagsByTechnologyId(@Param("techId") Long techId);
}
