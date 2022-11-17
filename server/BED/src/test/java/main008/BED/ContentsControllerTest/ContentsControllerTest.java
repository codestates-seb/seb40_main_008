package main008.BED.ContentsControllerTest;

import com.google.gson.Gson;
import main008.BED.contents.controller.ContentsController;
import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.contents.service.ContentsService;
import main008.BED.helper.StubData;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContentsController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class ContentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersMapper usersMapper;

    @MockBean
    private UsersService usersService;

    @MockBean
    private ContentsMapper contentsMapper;

    @MockBean
    private ContentsService contentsService;

    @Autowired
    private Gson gson;

    @Test
    public void postContentsTest() throws Exception {

        // given
        UsersDto.Post postUser = (UsersDto.Post) StubData.MockUser.getRequestBody(HttpMethod.POST);
        UsersDto.UserResponseToMyPage responseUser = StubData.MockUser.getMyPageUser();

        given(usersMapper.postToEntity(Mockito.any(UsersDto.Post.class))).willReturn(new Users());
        given(usersService.createUsers(Mockito.any(Users.class))).willReturn(new Users());
        given(usersMapper.usersToMyPage(Mockito.any(Users.class))).willReturn(responseUser);

        ContentsDto.Post contentsPost = (ContentsDto.Post) StubData.MockContents.getRequestBody(HttpMethod.POST);
        ContentsDto.Response responseContents = StubData.MockContents.getSingleContentResponseBody();

        given(contentsMapper.postToContents(Mockito.any(ContentsDto.Post.class))).willReturn(new Contents());
        given(contentsService.createContents(Mockito.any(Contents.class), eq(responseUser.getUsersId()))).willReturn(new Contents());
        given(contentsMapper.contentsToResponse(Mockito.any(Contents.class))).willReturn(responseContents);

        String content = gson.toJson(contentsPost);

        // when
        ResultActions actions = mockMvc.perform(
                post("/auth/home/{users-id}/uploadcontents", responseUser.getUsersId())
//                post("/auth/home/mypage/uploadcontents")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        // then
        actions.andExpect(status().isCreated())
                .andDo(document("post-contents",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("users-id").description("회원 식별자 ID")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("클래스 제목"),
                                        fieldWithPath("categories").type(JsonFieldType.STRING).description("클래스 카테고리"),
                                        fieldWithPath("details").type(JsonFieldType.STRING).description("클래스 소개"),
                                        fieldWithPath("tutorDetail").type(JsonFieldType.STRING).description("클래스 게시자 소개"),
                                        fieldWithPath("thumbnail").type(JsonFieldType.STRING).description("클래스 썸네일")
                                )
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("contentsId").type(JsonFieldType.NUMBER).description("클래스 식별자"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("클래스 제목"),
                                        fieldWithPath("thumbnail").type(JsonFieldType.STRING).description("클래스 썸네일"),
                                        fieldWithPath("categories").type(JsonFieldType.STRING).description("카테고리"),
                                        fieldWithPath("users").type(JsonFieldType.OBJECT).description("클래스 작성자"),
                                        fieldWithPath("users.usersId").type(JsonFieldType.NUMBER).description("작성자 식별자"),
                                        fieldWithPath("users.userName").type(JsonFieldType.STRING).description("작성자 별칭"),
                                        fieldWithPath("users.profileImage").type(JsonFieldType.STRING).description("작성자 프로필 사진")

                        )))).andReturn();
    }
}
