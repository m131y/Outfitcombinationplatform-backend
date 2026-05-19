package com.my131.Outfitcombinationplatform_backend.domain.post.repository;

import com.my131.Outfitcombinationplatform_backend.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
