package com.category.service.web.category;

import static com.category.service.utils.RestDocFormatGenerator.getDateTimeFormat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import com.category.service.category.CreateCategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@DisplayName("카테고리 생성")
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = CreateCategoryRestController.class)
@MockBean(JpaMetamodelMappingContext.class)
class CreateCategoryRestControllerTest {

  final CategoryMockHelper helper = new CategoryMockHelper();

  MockMvc mockMvc;

  @MockBean
  CreateCategoryUseCase createCategoryUseCase;

  @BeforeEach
  void setUp(WebApplicationContext webApplicationContext,
             RestDocumentationContextProvider restDocumentation) {
    this.mockMvc = MockMvcBuilders
        .webAppContextSetup(webApplicationContext)
        .apply(documentationConfiguration(restDocumentation))
        .alwaysDo(print())
        .addFilters(new CharacterEncodingFilter("UTF-8", true))
        .build();

    Mockito.mockStatic(CreateCategoryResponse.class);
  }

  @Test
  @DisplayName("카테고리 생성 API")
  void create() throws Exception {
    var givenId = 1L;
    var givenCategory = helper.createCategory();
    ReflectionTestUtils.setField(givenCategory, "id", givenId);
    ReflectionTestUtils.setField(givenCategory, "createdDateTime", LocalDateTime.now());

    given(createCategoryUseCase.create(any()))
        .willReturn(givenCategory);

    var createCategoryResponse = helper.createCategoryResponse(givenCategory);

    given(CreateCategoryResponse.of(any()))
        .willReturn(createCategoryResponse);

    // when
    final ResultActions resultActions = mockMvc.perform(
        post("/api/categories")
            .content(helper.createCategoryRequest())
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
    );

    resultActions
        .andExpect(status().isOk())
        .andDo(
            document("categories/{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    requestSnippets()
                ),
                responseFields(
                    beneathPath("data").withSubsectionId("data"),
                    resultsSnippets()
                )
            ));
  }

  private List<FieldDescriptor> requestSnippets() {
    return List.of(
        fieldWithPath("parentId").type(NUMBER).description("부모 카테고리 식별자").optional(),
        fieldWithPath("name").type(STRING).description("카테고리 명"),
        fieldWithPath("sort").type(NUMBER).description("순서")
    );
  }

  private List<FieldDescriptor> resultsSnippets() {
    return List.of(
        fieldWithPath("id").type(NUMBER).description("카테고리 고유 번호"),
        fieldWithPath("name").type(STRING).description("카테고리 명"),
        fieldWithPath("sort").type(NUMBER).description("순서"),
        fieldWithPath("createdDateTime").type(STRING).description("등록 일시").attributes(getDateTimeFormat())
    );
  }
}
