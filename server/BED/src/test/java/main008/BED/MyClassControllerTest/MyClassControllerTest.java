package main008.BED.MyClassControllerTest;

import main008.BED.helper.StubData;
import main008.BED.myClass.controller.MyClassController;
import main008.BED.myClass.dto.MyClassDto;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myClass.mapper.MyClassMapper;
import main008.BED.myClass.service.MyClassService;
import main008.BED.myUploadClass.dto.MyUploadClassDto;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.users.dto.UsersDto;
import main008.BED.users.entity.Users;
import main008.BED.users.mapper.UsersMapper;
import main008.BED.users.service.UsersService;
import main008.BED.wish.dto.WishDto;
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
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyClassController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MyClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersMapper usersMapper;

    @MockBean
    private UsersService usersService;

    @MockBean
    private MyClassMapper myClassMapper;

    @MockBean
    private MyClassService myClassService;

    @Test
    public void getMyWishClass() throws Exception {

        // given
        Long usersId = 1L;
        UsersDto.Post post = (UsersDto.Post) StubData.MockUser.getRequestBody(HttpMethod.POST);

        given(usersMapper.postToEntity(Mockito.any(UsersDto.Post.class))).willReturn(new Users());
        given(usersService.createUsers(Mockito.any(Users.class))).willReturn(new Users());

        MyClassDto.WishClassResponse wishClassResponse = StubData.MockMyClass.getMyWishClassResponseBody();

        given(myClassService.getWishClass(eq(usersId))).willReturn(new MyClass());
        given(myClassMapper.myClassToWishResponse(Mockito.any(MyClass.class))).willReturn(wishClassResponse);

        // when
        ResultActions actions = mockMvc.perform(
                get("/auth/home/{users-id}/myclass/wishclass", usersId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andDo(document("get-mywishclass",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("users-id").description("회원 식별자 ID")
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("wishes").type(JsonFieldType.ARRAY).description("찜 목록"),
                                        fieldWithPath("wishes[].wished").type(JsonFieldType.BOOLEAN).description("찜 유무"),
                                        fieldWithPath("wishes[].contents").type(JsonFieldType.OBJECT).description("클래스 정보"),
                                        fieldWithPath("wishes[].contents.contentsId").type(JsonFieldType.NUMBER).description("클래스 식별자 ID"),
                                        fieldWithPath("wishes[].contents.title").type(JsonFieldType.STRING).description("클래스 제목"),
                                        fieldWithPath("wishes[].contents.thumbnail").type(JsonFieldType.STRING).description("클래스 썸네일"),
                                        fieldWithPath("wishes[].contents.categories").type(JsonFieldType.STRING).description("클래스 카테고리"),
                                        fieldWithPath("wishes[].contents.users").type(JsonFieldType.OBJECT).description("클래스 작성자"),
                                        fieldWithPath("wishes[].contents.users.usersId").type(JsonFieldType.NUMBER).description("작성자 식별자 ID"),
                                        fieldWithPath("wishes[].contents.users.userName").type(JsonFieldType.STRING).description("작성자 별칭"),
                                        fieldWithPath("wishes[].contents.users.profileImage").type(JsonFieldType.STRING).description("작성자 프로필 사진")
                                )
                        ))).andReturn();
    }
}
