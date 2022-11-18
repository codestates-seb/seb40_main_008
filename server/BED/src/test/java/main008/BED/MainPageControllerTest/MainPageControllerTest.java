package main008.BED.MainPageControllerTest;

import main008.BED.helper.StubData;
import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.mainPage.controller.MainPageController;
import main008.BED.mainPage.dto.MainPageDto;
import main008.BED.mainPage.entity.MainPage;
import main008.BED.mainPage.mapper.MainPageMapper;
import main008.BED.mainPage.service.MainPageService;
import main008.BED.userPage.mapper.UserPageMapper;
import main008.BED.userPage.service.UserPageService;
import main008.BED.users.dto.UsersDto;
import main008.BED.users.entity.Users;
import main008.BED.users.mapper.UsersMapper;
import main008.BED.users.service.UsersService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static main008.BED.utils.ApiDocumentUtils.getRequestPreProcessor;
import static main008.BED.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
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
    private MainPageMapper mainPageMapper;

    @MockBean
    private ContentsMapper contentsMapper;

    @MockBean
    private UsersMapper usersMapper;

    @MockBean
    private UsersService usersService;

    @MockBean
    private UserPageService userPageService;

    @MockBean
    private UserPageMapper userPageMapper;

    @Test
    public void getNotLoginMainPage() throws Exception {

        // given
        List<ContentsDto.Response> contentsResponse = StubData.MockContents.getContentResponseBody();

        MainPageDto.NotLoginResponse response = new MainPageDto.NotLoginResponse(contentsResponse);

        given(mainPageService.getHome()).willReturn(new MainPage());
        given(mainPageMapper.mainPageToNotLoginResponse(Mockito.any(MainPage.class), eq(contentsMapper), eq(usersMapper))).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(
                get("/home")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
//                .andExpect(jsonPath("$.contentsList").value(contentsResponse)) 리퀘스트가 없으므로 현재 필요 없음
                .andDo(
                        document("get-notLoginHome",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                responseFields(
                                        Arrays.asList(
                                                fieldWithPath("contentsList").type(JsonFieldType.ARRAY).description("클래스 리스트"),
                                                fieldWithPath("contentsList[].contentsId").type(JsonFieldType.NUMBER).description("클래스 식별자 ID"),
                                                fieldWithPath("contentsList[].title").type(JsonFieldType.STRING).description("클래스 제목"),
                                                fieldWithPath("contentsList[].thumbnail").type(JsonFieldType.STRING).description("클래스 썸네일"),
                                                fieldWithPath("contentsList[].categories").type(JsonFieldType.STRING).description("카테고리"),
                                                fieldWithPath("contentsList[].users").type(JsonFieldType.OBJECT).description("클래스 작성자"),
                                                fieldWithPath("contentsList[].users.usersId").type(JsonFieldType.NUMBER).description("작성자 식별자 ID"),
                                                fieldWithPath("contentsList[].users.userName").type(JsonFieldType.STRING).description("작성자 별칭"),
                                                fieldWithPath("contentsList[].users.profileImage").type(JsonFieldType.STRING).description("작성자 프로필 사진")
                                        )
                                ))
                )
                .andReturn();
    }

    @Test
    public void getLoginHome() throws Exception {

        // given
        UsersDto.Post post = (UsersDto.Post) StubData.MockUser.getRequestBody(HttpMethod.POST);

        UsersDto.UserResponseToMyPage responseToMyPage = StubData.MockUser.getMyPageUser();

        given(usersMapper.postToEntity(Mockito.any(UsersDto.Post.class))).willReturn(new Users());
        given(usersService.createUsers(Mockito.any(Users.class))).willReturn(new Users());
        given(usersMapper.usersToMyPage(Mockito.any(Users.class))).willReturn(responseToMyPage);

        List<ContentsDto.Response> contentsResponse = StubData.MockContents.getContentResponseBody();

        MainPageDto.LoginResponse response = new MainPageDto.LoginResponse(responseToMyPage.getUsersId(), contentsResponse);

        given(mainPageService.getHome()).willReturn(new MainPage());
        given(mainPageMapper.mainPageToLoginResponse(Mockito.any(MainPage.class), eq(contentsMapper), eq(responseToMyPage.getUsersId()), eq(usersMapper))).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(
                get("/auth/home")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
//                .andExpect(jsonPath("$.email").value(responseToMyPage.getEmail()))
                .andDo(
                        document("get-LoginHome",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                responseFields(
                                        Arrays.asList(
                                                fieldWithPath("usersId").type(JsonFieldType.NUMBER).description("현 로그인 회원 식별자 ID"),
                                                fieldWithPath("contentsList").type(JsonFieldType.ARRAY).description("클래스 리스트"),
                                                fieldWithPath("contentsList[].contentsId").type(JsonFieldType.NUMBER).description("클래스 식별자 ID"),
                                                fieldWithPath("contentsList[].title").type(JsonFieldType.STRING).description("클래스 제목"),
                                                fieldWithPath("contentsList[].thumbnail").type(JsonFieldType.STRING).description("클래스 썸네일"),
                                                fieldWithPath("contentsList[].categories").type(JsonFieldType.STRING).description("카테고리"),
                                                fieldWithPath("contentsList[].users").type(JsonFieldType.OBJECT).description("클래스 작성자"),
                                                fieldWithPath("contentsList[].users.usersId").type(JsonFieldType.NUMBER).description("작성자 식별자 ID"),
                                                fieldWithPath("contentsList[].users.userName").type(JsonFieldType.STRING).description("작성자 별칭"),
                                                fieldWithPath("contentsList[].users.profileImage").type(JsonFieldType.STRING).description("작성자 프로필 사진")
                                        )
                                ))
                )
                .andReturn();
    }
}
