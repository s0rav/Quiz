package com.crio.qa.repositories;

import com.crio.qa.entities.QuestionEntity;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<QuestionEntity, String> {
  List<QuestionEntity> findByModuleId(String moduleId);
}
