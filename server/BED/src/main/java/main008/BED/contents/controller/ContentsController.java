package main008.BED.contents.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.S3.S3Service;
import main008.BED.bookmark.entity.Bookmark;
import main008.BED.bookmark.mapper.BookmarkMapper;
import main008.BED.bookmark.service.BookmarkService;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.chapter.mapper.ChapterMapper;
import main008.BED.chapter.service.ChapterService;
import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.entity.EnumModel;
import main008.BED.contents.entity.EnumValue;
import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.contents.service.ContentsService;
import main008.BED.converter.StringToCategoryEnum;
import main008.BED.docs.mapper.DocsMapper;
import main008.BED.dto.ContentsMultiResponseDto;
import main008.BED.dto.PageInfo;
import main008.BED.likes.service.LikesService;
import main008.BED.payment.dto.PaymentDto;
import main008.BED.payment.entity.Payment;
import main008.BED.payment.mapper.PaymentMapper;
import main008.BED.review.mapper.ReviewMapper;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.service.UploadClassService;
import main008.BED.users.entity.Users;
import main008.BED.users.mapper.UsersMapper;
import main008.BED.users.service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class ContentsController {

    private final ContentsService contentsService;
    private final ContentsMapper contentsMapper;
    private final UsersService usersService;
    private final UsersMapper usersMapper;
    private final S3Service s3Service;
    private final DocsMapper docsMapper;
    private final BookmarkService bookmarkService;
    private final BookmarkMapper bookmarkMapper;
    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;
    private final PaymentMapper paymentMapper;
    private final ReviewMapper reviewMapper;
    private final UploadClassService uploadClassService;
    private final LikesService likesService;


    // 컨텐츠 개설
    @PostMapping("/auth/uploadcontents")
    public ResponseEntity postContents(Principal principal,
                                       @RequestParam("title") String title,
                                       @RequestParam("categories") String categories,
                                       @RequestParam("details") String details,
                                       @RequestParam("tutorDetail") String tutorDetail,
                                       @RequestParam("thumbnail") MultipartFile thumbnail,
                                       @RequestParam("price") String price) throws UnsupportedEncodingException {

        Users user = usersService.findVerifiedUserByEmail(principal.getName());

        // thumbnail -> S3 업로드
        HashMap map = s3Service.uploadToS3(thumbnail, "/contents/thumbnail");
        String fileKey = map.get("fileKey").toString();
        String thumbnailUrl = map.get("url").toString();

        PaymentDto.Post paymentPost = paymentMapper.reqToPost(Integer.parseInt(price));
        Payment payment = paymentMapper.postToEntity(paymentPost);

//        Contents.Categories category = stringToCategoryEnum.convert(categories);
        Contents.Categories category = contentsService.engOfKor(categories);

        ContentsDto.Post post = contentsMapper.reqToContentsPost(title, category, details, tutorDetail, thumbnailUrl, fileKey);
        Contents contents = contentsService.createContents(contentsMapper.postToContents(post), user.getUsersId(), payment);

        return new ResponseEntity<>(contentsMapper.contentsToResponse(contents), HttpStatus.CREATED);
    }


    // 컨텐츠 찜 기능
    @PostMapping("/auth/{contents-id}/wish")
    public ResponseEntity wishContents(Principal principal,
                                       @PathVariable("contents-id") @Positive Long contentsId,
                                       @RequestParam(name = "wished", required = false, defaultValue = "true") Boolean wished) {

        Users users = usersService.findVerifiedUserByEmail(principal.getName());

        contentsService.wishContents(contentsId, users.getUsersId(), wished);

        return ResponseEntity.status(HttpStatus.OK).body("Update your wishlist.");
    }

    /**
     * READ: 컨텐츠 상세화면 Response DTO
     */
    @GetMapping("/contents/{contents-id}")
    public ResponseEntity getContent(@PathVariable("contents-id") @Positive Long contentsId,
                                     Principal principal) {

        Users users = usersService.findVerifiedUserByEmail(principal.getName());

        Contents contents = contentsService.readContent(contentsId);

        ChapterDto.CurriculumInContent curriculumInContent = chapterService.readCurriculumInContent(contentsId);

        HashMap<String, String> roleAndWish = contentsService.userRoleDivision(contents, principal);
        Boolean liked = likesService.getContentLike(contents, users);

        ContentsDto.ResponseInContent responseInContent =
                contentsMapper.contentToResponseInContent(contents, roleAndWish, contentsService, liked);

        return new ResponseEntity<>(
                new ContentsMultiResponseDto<>(responseInContent, curriculumInContent.getCurriculumInfo()), HttpStatus.OK);
    }

    /**
     * READ: 영상 재생 화면 Response DTO
     */
    @GetMapping("/auth/contents/{contents-id}/video/{uploadClass-id}")
    public ResponseEntity getStream(Principal principal,
                                    @PathVariable("contents-id") @Positive Long contentsId,
                                    @PathVariable("uploadClass-id") @Positive Long uploadClassId) {

        Contents contents = contentsService.readContent(contentsId);

        contentsService.authorizationForPay(contents, principal); // 구매 여부 판단

        UploadClass uploadClass = uploadClassService.readClassById(uploadClassId);
        ChapterDto.CurriculumInStream curriculumInStream = chapterService.readCurriculumInStream(contentsId);

        List<Bookmark> bookmarkList = bookmarkService.findBookmarkListByUsersId(principal, uploadClassId); // User 본인의 메모만 전송

        ContentsDto.ResponseForStream responseForStream =
                contentsMapper.contentsResponseForStream(
                        contents, uploadClass,
                        usersMapper, docsMapper,
                        reviewMapper, bookmarkMapper,
                        bookmarkList, curriculumInStream.getCurriculumInfo());


        return new ResponseEntity<>(responseForStream, HttpStatus.OK);
    }

    /**
     * 카테고리 조회
     */
    @GetMapping("/search") // API에 대문자는 들어가면 안됨,,,, RESTful API
    public ResponseEntity getCategories(@RequestParam("categories") Contents.Categories categories,
                                        @RequestParam(name = "sort", required = false, defaultValue = "popular") String sort) {

        List<Contents> contents = contentsService.findContentsByCategory(categories, sort);

        List<ContentsDto.ResponseForCategories> categories1 =
                contentsMapper.contentsToCategoriesResponses(contents, usersMapper);

        return new ResponseEntity<>(contentsMapper.toCategoryList(categories1), HttpStatus.OK);
    }


    /**
     * Search: Contents Title 검색 - 최신순
     */
    @GetMapping("/search/title/lateast")
    public ResponseEntity getTitleContentsByLateast(@RequestParam("keyword") String keyword,
                                           @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                           @RequestParam(name = "size", required = false, defaultValue = "10") int size) {


        Page<Contents> contentsPage = contentsService.searchTitleContentsByLateast(keyword, page, size);
        PageInfo pageInfo = PageInfo.of(contentsPage);

//        List<ContentsDto.ResponseForTitleSearch> responseForTitleSearch
//                = contentsMapper.contentsPageToResponses(contentsPage.getContent()); // 페이징 리스트
        List<ContentsDto.ResponseForCategories> searchList = contentsMapper.contentsToCategoriesResponses(contentsPage.getContent(), usersMapper);


        return new ResponseEntity(contentsMapper.toCategoryList(searchList), HttpStatus.OK);
    }


    /**
     * Search: Contents Title 검색 - 인기순
     */
    @GetMapping("/search/title")
    public ResponseEntity getTitleContentsByPopular(@RequestParam("keyword") String keyword,
                                           @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                           @RequestParam(name = "size", required = false, defaultValue = "10") int size) {


        Page<Contents> contentsPage = contentsService.searchTitleContentsByPopular(keyword, page, size);
        PageInfo pageInfo = PageInfo.of(contentsPage);

//        List<ContentsDto.ResponseForTitleSearch> responseForTitleSearch
//                = contentsMapper.contentsPageToResponses(contentsPage.getContent());
        List<ContentsDto.ResponseForCategories> searchList = contentsMapper.contentsToCategoriesResponses(contentsPage.getContent(), usersMapper);


        return new ResponseEntity(contentsMapper.toCategoryList(searchList), HttpStatus.OK);
    }

    /**
     * 카테고리 명칭 리스트 출력 위한 로직
     * @return 카테고리 명칭 리스트
     */
    @GetMapping("/categoryList")
    public Map<String, List<EnumValue>> getCategoryList() {

        Map<String, List<EnumValue>> enumValues = new LinkedHashMap<>();

        enumValues.put("카테고리 명칭 리스트", toEnumValues(Contents.Categories.class));

        return enumValues;

    }

    private List<EnumValue> toEnumValues(Class<? extends EnumModel> e){

        return Arrays
                .stream(e.getEnumConstants())
                .map(EnumValue::new)
                .collect(Collectors.toList());
    }

    /**
     * DELETE: Contents 삭제
     */
    @DeleteMapping("/auth/contents/{contents-id}")
    public ResponseEntity deleteContents(@PathVariable("contents-id") @Positive Long contentsId,
                                         Principal principal) {

        contentsService.removeContents(contentsId, principal);
        return new ResponseEntity("The Content is removed.", HttpStatus.OK);
    }

    /**
     * PATCH: Contents 수정
     */
    @PatchMapping("/auth/contents/{contents-id}")
    public ResponseEntity patchContents(@PathVariable("contents-id") @Positive Long contentsId,
                                        @RequestParam("title") String title,
                                        @RequestParam("categories") String categories,
                                        @RequestParam("details") String details,
                                        @RequestParam("tutorDetail") String tutorDetail,
                                        @RequestParam("thumbnail") MultipartFile thumbnail,
                                        @RequestParam("price") String price,
                                        Principal principal) throws UnsupportedEncodingException {

        Contents contents = contentsService.readContent(contentsId);
        String oldFileKey = contents.getFileKey();

        // thumbnail -> S3 업로드
        HashMap map = s3Service.updateToS3(thumbnail, "/contents/thumbnail", oldFileKey);
        String fileKey = map.get("fileKey").toString();
        String thumbnailUrl = map.get("url").toString();

//        Contents.Categories category = stringToCategoryEnum.convert(categories);
        Contents.Categories category = contentsService.engOfKor(categories);

        PaymentDto.Patch paymentPatch = new PaymentDto.Patch(Integer.parseInt(price));

        Payment payment = paymentMapper.patchToEntity(paymentPatch);

        ContentsDto.Patch patch =
                contentsMapper.reqToContentsPatch(title, category, details, tutorDetail, thumbnailUrl, fileKey);

        contentsService.updateContents(contentsId, principal, contentsMapper.patchToContents(patch), payment);

        return new ResponseEntity<>("The Content is updated.", HttpStatus.OK);
    }

    /**
     * GET: 모든 컨텐츠 아이디 리스트 조회
     */
    @GetMapping("/contents")
    public ResponseEntity getContentsIdList() {
        List<Contents> contents = contentsService.readContentsIdList();
        List<ContentsDto.ContentsId> contentsIds = contentsMapper.contentsToId(contents, chapterMapper);
        return new ResponseEntity(contentsIds, HttpStatus.OK);
    }

}
