package main008.BED.helper;

import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.entity.Contents;
import main008.BED.myUploadClass.dto.MyUploadClassDto;
import main008.BED.userPage.dto.UserPageDto;
import main008.BED.users.dto.UsersDto;
import main008.BED.users.entity.Users;
import org.springframework.http.HttpMethod;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main008.BED.contents.entity.Contents.Categories.PROGRAMMING;

public class StubData {

    private static final Map<HttpMethod, Object> stubRequestBody;
    static {
        stubRequestBody = new HashMap<>();
        stubRequestBody.put(HttpMethod.POST, new UsersDto.Post(
                "User1@gmail.com", "유저1", "프로필 사진"));
    }

    private static final Map<HttpMethod, Object> stubContentsRequestBody;
    static {
        stubContentsRequestBody = new HashMap<>();
        stubContentsRequestBody.put(HttpMethod.POST, new ContentsDto.Post(
                "이건 제목이다"
                , PROGRAMMING
                , "이건 강좌소개이다"
                , "이건 강사소개이다"
                , "이건 강좌 썸네일이다"
        ));
    }

    public static class MockUser {
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static UsersDto.UserResponseToMyPage getMyPageUser() {

            return new UsersDto.UserResponseToMyPage(
                    1L,
                    "유저1",
                    "User1@gmail.com",
                    "프로필 사진",
                    5000
            );
        }
    }

    public static class MockUserPage {
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static UserPageDto.Response getUserPage() {

            return new UserPageDto.Response(
                    new UsersDto.UserResponseToMyPage(
                            1L,
                            "유저1",
                            "user1@gmail.com",
                            "프로필사진",
                            5000
                    ));
        }
    }

    public static class MockContents {
        public static Object getRequestBody(HttpMethod method) {
            return stubContentsRequestBody.get(method);
        }

        public static ContentsDto.Response getSingleContentResponseBody() {

            return new ContentsDto.Response(
                    1L,
                    "이건 제목이다",
                    "이건 강좌 썸네일이다",
                    PROGRAMMING,
                    new UsersDto.UserResponseToHome(
                            1L,
                            "유저1",
                            "프로필 사진"));
        }

        public static List<ContentsDto.Response> getContentResponseBody() {

            return List.of(
                    new ContentsDto.Response(
                            3L,
                            "title3",
                            "title3 thumbnail",
                            PROGRAMMING,
                            new UsersDto.UserResponseToHome(
                                    1L,
                                    "testUser1",
                                    "testUser1 profileImage")),
                    new ContentsDto.Response(
                            2L,
                            "title2",
                            "title2 thumbnail",
                            Contents.Categories.BAKING_DESSERT,
                            new UsersDto.UserResponseToHome(
                                    2L,
                                    "testUser2",
                                    "testUser2 profileImage")),
                    new ContentsDto.Response(
                            1L,
                            "title1",
                            "title1 thumbnail",
                            Contents.Categories.CRAFTS,
                            new UsersDto.UserResponseToHome(
                                    3L,
                                    "testUser3",
                                    "testUser3 profileImage")));
        }
    }

    public static class MockMyUploadClass {

        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static MyUploadClassDto.Response getMyUploadClassResponseBody() {

            return new MyUploadClassDto.Response(List.of(
                    new ContentsDto.Response(
                            21L,
                            "title21",
                            "title21 thumbnail",
                            PROGRAMMING,
                            new UsersDto.UserResponseToHome(
                                    1L,
                                    "User1",
                                    "User1 profileImage")),
                    new ContentsDto.Response(
                            16L,
                            "title16",
                            "title16 thumbnail",
                            PROGRAMMING,
                            new UsersDto.UserResponseToHome(
                                    1L,
                                    "User1",
                                    "User1 profileImage")),
                    new ContentsDto.Response(
                            5L,
                            "title15",
                            "title5 thumbnail",
                            PROGRAMMING,
                            new UsersDto.UserResponseToHome(
                                    1L,
                                    "User1",
                                    "User1 profileImage"))));
        }
    }
}
