package main008.BED.UserPageControllerTest;

import main008.BED.helper.StubData;
import main008.BED.userPage.controller.UserPageController;
import main008.BED.userPage.dto.UserPageDto;
import main008.BED.userPage.entity.UserPage;
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

import static main008.BED.utils.ApiDocumentUtils.getRequestPreProcessor;
import static main008.BED.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserPageController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class UserPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersMapper usersMapper;

    @MockBean
    private UsersService usersService;

    @MockBean
    private UserPageService userPageService;

    @MockBean
    private UserPageMapper userPageMapper;

    @Test
    public void getMypage() throws Exception {

        // given
        UsersDto.Post post = (UsersDto.Post) StubData.MockUser.getRequestBody(HttpMethod.POST);

        UsersDto.UserResponseToMyPage responseUser = StubData.MockUser.getMyPageUser();
        UserPageDto.Response responseToMyPage = StubData.MockUserPage.getUserPage();

        given(usersMapper.postToEntity(Mockito.any(UsersDto.Post.class))).willReturn(new Users());
        given(usersService.createUsers(Mockito.any(Users.class))).willReturn(new Users());
        given(usersMapper.usersToMyPage(Mockito.any(Users.class))).willReturn(responseUser);
        given(userPageService.findUserPage(eq(responseUser.getUsersId()))).willReturn(new UserPage());
        given(userPageMapper.userPageToResponse(Mockito.any(UserPage.class))).willReturn(responseToMyPage);

        Long usersId = responseToMyPage.getUsers().getUsersId();

        // when
        ResultActions actions = mockMvc.perform(
                get("/auth/home/{users-id}", usersId)
//                get("/auth/home/mypage")         // principal 적용 시 이걸로 변경해야함
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andDo(document("get-mypage",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("users-id").description("회원 식별자 ID")
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("users").type(JsonFieldType.OBJECT).description("회원 정보"),
                                        fieldWithPath("users.usersId").type(JsonFieldType.NUMBER).description("회원 식별자 ID"),
                                        fieldWithPath("users.userName").type(JsonFieldType.STRING).description("회원 닉네임"),
                                        fieldWithPath("users.email").type(JsonFieldType.STRING).description("회원 아이디"),
                                        fieldWithPath("users.profileImage").type(JsonFieldType.STRING).description("회원 프로필 사진"),
                                        fieldWithPath("users.coin").type(JsonFieldType.NUMBER).description("보유 코인 개수")
                                )
                        ))).andReturn();

    }}
