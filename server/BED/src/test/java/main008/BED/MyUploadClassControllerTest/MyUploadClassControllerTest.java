package main008.BED.MyUploadClassControllerTest;

import main008.BED.helper.StubData;
import main008.BED.myUploadClass.controller.MyUploadClassController;
import main008.BED.myUploadClass.dto.MyUploadClassDto;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.myUploadClass.mapper.MyUploadClassMapper;
import main008.BED.myUploadClass.service.MyUploadClassService;
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

@WebMvcTest(MyUploadClassController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MyUploadClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUploadClassMapper myUploadClassMapper;

    @MockBean
    private MyUploadClassService myUploadClassService;

    @MockBean
    private UsersMapper usersMapper;

    @MockBean
    private UsersService usersService;

    @Test
    public void getMyUploadClass() throws Exception {

        // given
        Long usersId = 1L;
        UsersDto.Post post = (UsersDto.Post) StubData.MockUser.getRequestBody(HttpMethod.POST);

        given(usersMapper.postToEntity(Mockito.any(UsersDto.Post.class))).willReturn(new Users());
        given(usersService.createUsers(Mockito.any(Users.class))).willReturn(new Users());

        MyUploadClassDto.Response myUploadClass = (MyUploadClassDto.Response) StubData.MockMyUploadClass.getMyUploadClassResponseBody();

        given(myUploadClassService.getMyUploadClasses(eq(usersId))).willReturn(new MyUploadClass());
        given(myUploadClassMapper.myUploadClassToResponse(Mockito.any(MyUploadClass.class))).willReturn(myUploadClass);

        // when
        ResultActions actions = mockMvc.perform(
                get("/auth/home/{users-id}/myuploadclass", usersId)
//                get("/auth/home/mypage/myuploadclass", usersId)        // principal 적용 시 이걸로 변경해야함
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andDo(document("get-myuploadclass",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("users-id").description("회원 식별자 ID")
//                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("contentsList").type(JsonFieldType.ARRAY).description("클래스 리스트"),
                                        fieldWithPath("contentsList[].contentsId").type(JsonFieldType.NUMBER).description("클래스 식별자"),
                                        fieldWithPath("contentsList[].title").type(JsonFieldType.STRING).description("클래스 제목"),
                                        fieldWithPath("contentsList[].thumbnail").type(JsonFieldType.STRING).description("클래스 썸네일"),
                                        fieldWithPath("contentsList[].categories").type(JsonFieldType.STRING).description("카테고리"),
                                        fieldWithPath("contentsList[].users").type(JsonFieldType.OBJECT).description("클래스 작성자"),
                                        fieldWithPath("contentsList[].users.usersId").type(JsonFieldType.NUMBER).description("작성자 식별자"),
                                        fieldWithPath("contentsList[].users.userName").type(JsonFieldType.STRING).description("작성자 별칭"),
                                        fieldWithPath("contentsList[].users.profileImage").type(JsonFieldType.STRING).description("작성자 프로필 사진")
                                )
                        ))).andReturn();
    }

}
