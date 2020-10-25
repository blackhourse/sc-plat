package com.mht.elasticsearchdemo1.repository;

import com.mht.elasticsearchdemo1.model.EsCmsArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CmsArticleRepository extends JpaRepository<EsCmsArticle, Long> {
}
