package com.my131.Outfitcombinationplatform_backend.domain.search.repository;

import com.my131.Outfitcombinationplatform_backend.domain.search.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Search, Long> {
}
