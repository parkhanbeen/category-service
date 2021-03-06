package com.category.service.web.category;

import static com.category.service.utils.RestDocFormatGenerator.getDateTimeFormat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.category.service.category.FindCategoryUseCase;
import com.category.service.category.entity.CategoryCustomRepositoryHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@DisplayName("???????????? ??????")
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = FindCategoryRestController.class)
@MockBean(JpaMetamodelMappingContext.class)
class FindCategoryRestControllerTest {

  final CategoryMockHelper helper = new CategoryMockHelper();
  final CategoryCustomRepositoryHelper repositoryHelper = new CategoryCustomRepositoryHelper();

  MockMvc mockMvc;

  MockedStatic<FindCategoryResponse> responseMockStatic;

  @MockBean
  FindCategoryUseCase findCategoryUseCase;

  @BeforeEach
  void setUp(WebApplicationContext webApplicationContext,
             RestDocumentationContextProvider restDocumentation) {
    this.mockMvc = MockMvcBuilders
        .webAppContextSetup(webApplicationContext)
        .apply(documentationConfiguration(restDocumentation))
        .alwaysDo(print())
        .addFilters(new CharacterEncodingFilter("UTF-8", true))
        .build();

    responseMockStatic = Mockito.mockStatic(FindCategoryResponse.class);
  }

  @AfterEach
  void after() {
    responseMockStatic.close();
  }

  @Test
  @DisplayName("???????????? ?????? ?????? API")
  void findCategories() throws Exception {
    var givenCategories = repositoryHelper.createCategories();

    given(findCategoryUseCase.findCategories())
        .willReturn(givenCategories);

    final var givenCategoryResponseList = helper.findCategoryResponseList(givenCategories);

    given(FindCategoryResponse.of(any()))
        .willReturn(givenCategoryResponseList);

    // when
    final ResultActions resultActions = mockMvc.perform(
        get("/api/categories")
            .accept(APPLICATION_JSON)
    );

    resultActions
        .andExpect(status().isOk())
        .andDo(
            document("categories/{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    beneathPath("data").withSubsectionId("data"),
                    resultsSnippets()
                )
            ));
  }

  @Test
  @DisplayName("?????? ???????????? ?????? API")
  void findCategoriesById() throws Exception {
    final var givenCategories = repositoryHelper.createCategoriesById();
    final var givenId = 1L;

    given(findCategoryUseCase.findCategoriesById(givenId))
        .willReturn(givenCategories);

    final var givenCategoryResponseList = helper.findCategoryResponseList(givenCategories);

    given(FindCategoryResponse.of(any()))
        .willReturn(givenCategoryResponseList);

    // when
    final ResultActions resultActions = mockMvc.perform(
        get("/api/categories/{categoryId}", givenId)
            .accept(APPLICATION_JSON)
    );

    resultActions
        .andExpect(status().isOk())
        .andDo(
            document("categories/{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    beneathPath("data").withSubsectionId("data"),
                    resultsSnippets()
                )
            ));
  }

  private List<FieldDescriptor> resultsSnippets() {
    return List.of(
        fieldWithPath("id").type(NUMBER).description("???????????? ?????? ??????"),
        fieldWithPath("name").type(STRING).description("???????????? ???"),
        fieldWithPath("sort").type(NUMBER).description("??????"),
        fieldWithPath("modifiedDateTime").type(STRING)
            .description("?????? ??????").attributes(getDateTimeFormat()),
        fieldWithPath("createdDateTime").type(STRING)
            .description("?????? ??????").attributes(getDateTimeFormat()),
        fieldWithPath("children[]").type(ARRAY).description("?????? ????????????").optional(),
        fieldWithPath("children[].id").type(NUMBER).description("???????????? ?????? ??????"),
        fieldWithPath("children[].name").type(STRING).description("???????????? ???"),
        fieldWithPath("children[].sort").type(NUMBER).description("??????"),
        fieldWithPath("children[].modifiedDateTime").type(STRING)
            .description("?????? ??????").attributes(getDateTimeFormat()),
        fieldWithPath("children[].createdDateTime").type(STRING)
            .description("?????? ??????").attributes(getDateTimeFormat())
    );
  }

}
