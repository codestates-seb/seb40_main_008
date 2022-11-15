package main008.BED.MainPageControllerTest;

import com.google.gson.Gson;
import main008.BED.MainPageControllerTest.helper.StubData;
import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.mainPage.controller.MainPageController;
import main008.BED.mainPage.dto.MainPageDto;
import main008.BED.mainPage.entity.MainPage;
import main008.BED.mainPage.mapper.MainPageMapper;
import main008.BED.mainPage.service.MainPageService;
import main008.BED.users.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static main008.BED.MainPageControllerTest.utils.ApiDocumentUtils.getRequestPreProcessor;
import static main008.BED.MainPageControllerTest.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainPageController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MainPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MainPageService mainPageService;

    @MockBean
    private MainPageMapper mapper;

    @MockBean
    private ContentsMapper contentsMapper;

    @MockBean
    private UsersMapper usersMapper;

    @Test
    public void getNotLoginMainPage() throws Exception {

        // given
        List<ContentsDto.Response> contentsResponse = StubData.MockContents.getContentResponseBody();

        MainPageDto.NotLoginResponse response = new MainPageDto.NotLoginResponse(contentsResponse);

        given(mainPageService.getNotLoginHome()).willReturn(new MainPage());
        given(mapper.mainPageToNotLoginResponse(Mockito.any(MainPage.class), eq(contentsMapper), eq(usersMapper))).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(
                get("/home")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
//                .andExpect(jsonPath("$.contentsList").value(contentsResponse))
                .andDo(
                        document("get-notLoginHome",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                responseFields(
                                        Arrays.asList(
                                                fieldWithPath("contentsList").type(JsonFieldType.ARRAY).description("클래스 리스트"),
                                                fieldWithPath("contentsList[].id").type(JsonFieldType.NUMBER).description("클래스 식별자"),
                                                fieldWithPath("contentsList[].title").type(JsonFieldType.STRING).description("클래스 제목"),
                                                fieldWithPath("contentsList[].thumbnail").type(JsonFieldType.STRING).description("클래스 썸네일"),
                                                fieldWithPath("contentsList[].categories").type(JsonFieldType.STRING).description("카테고리"),
                                                fieldWithPath("contentsList[].users").type(JsonFieldType.OBJECT).description("클래스 작성자"),
                                                fieldWithPath("contentsList[].users.id").type(JsonFieldType.NUMBER).description("작성자 식별자"),
                                                fieldWithPath("contentsList[].users.username").type(JsonFieldType.STRING).description("작성자 별칭"),
                                                fieldWithPath("contentsList[].users.profileImage").type(JsonFieldType.STRING).description("작성자 프로필 사진")
                                        )
                                ))
                )
                .andReturn();
    }
}
