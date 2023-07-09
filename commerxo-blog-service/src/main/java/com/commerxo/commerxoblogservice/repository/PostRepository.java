package com.commerxo.commerxoblogservice.repository;

import com.commerxo.commerxoblogservice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    Optional<Post> findByTitle(String title);

}
