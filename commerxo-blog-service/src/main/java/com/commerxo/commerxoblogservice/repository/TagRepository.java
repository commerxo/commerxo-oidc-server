package com.commerxo.commerxoblogservice.repository;

import com.commerxo.commerxoblogservice.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    Optional<Tag> findByTagName(String name);
}
