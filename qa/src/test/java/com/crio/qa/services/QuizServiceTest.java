package com.crio.qa.services;

import com.crio.qa.entities.QuestionEntity;
import com.crio.qa.exchanges.GetModuleResponse;
import com.crio.qa.exchanges.QuestionGetResponse;
import com.crio.qa.repositories.QuestionRepository;
import com.crio.qa.utils.FixtureHelpers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

  @InjectMocks
  protected QuizService quizService;

  protected static final String FIXTURES = "resources/fixtures/exchanges";
  protected static final String QUESTIONNAIRE = "/initial_data_load.json";

  @Mock
  QuestionRepository questionRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
    new ObjectMapper();
  }

  @Test
  public void getQuestions() throws JsonParseException, JsonMappingException, IOException {
    lenient().when(questionRepository.findAll()).thenReturn(new ArrayList<>());
    GetModuleResponse response = quizService.getQuestions("1");
    assertEquals(0, response.getQuestions().size());
  }
}