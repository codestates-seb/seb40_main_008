package main008.BED.MainPageControllerTest.helper;

import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.entity.Contents;
import main008.BED.users.dto.UsersDto;
import main008.BED.users.entity.Users;
import org.springframework.http.HttpMethod;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StubData {

    private static Map<HttpMethod, Object> stubRequestBody;
    static {
        stubRequestBody = new HashMap<>();
    }

    public static class MockContents {
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static List<ContentsDto.Response> getContentResponseBody() {

            return List.of(
                    new ContentsDto.Response(
                            3L,
                            "title3",
                            "title3 thumbnail",
                            Contents.Categories.PROGRAMMING,
                            new UsersDto.Response(
                                    1L,
                                    "testUser1",
                                    "testUser1 profileImage")),
                    new ContentsDto.Response(
                            2L,
                            "title2",
                            "title2 thumbnail",
                            Contents.Categories.BAKING_DESSERT,
                            new UsersDto.Response(
                                    2L,
                                    "testUser2",
                                    "testUser2 profileImage")),
                    new ContentsDto.Response(
                            1L,
                            "title1",
                            "title1 thumbnail",
                            Contents.Categories.CRAFTS,
                            new UsersDto.Response(
                                    3L,
                                    "testUser3",
                                    "testUser3 profileImage")));
        }
    }
}
