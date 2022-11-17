package main008.BED.contents.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import main008.BED.likes.entity.Likes;
import main008.BED.likes.entity.LikesDetail;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myClass.repository.MyClassRepository;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.myUploadClass.repository.MyUploadClassRepository;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import main008.BED.wish.entity.Wish;
import main008.BED.wish.repository.WishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentsService {

    private final ContentsRepository contentsRepository;
    private final UsersRepository usersRepository;
    private final WishRepository wishRepository;
    private final MyUploadClassRepository myUploadClassRepository;
    private final MyClassRepository myClassRepository;

    // contents 올리기
    public Contents createContents(Contents contents, Long usersId) {

        contents.setWishes(new ArrayList<>());
  //      contents.setLikes(new Likes());

  //      Likes likes = contents.getLikes();
  //      likes.setContents(contents);
  //      likes.setCount(0);
  //      likes.setLikesDetails(new ArrayList<>());

        Contents contents1 = contentsRepository.save(contents);

        Users users = usersRepository.findByUsersId(usersId);
        contents1.setUsers(users);

        MyUploadClass myUploadClass = myUploadClassRepository.findByUsersUsersId(usersId);
        List<Contents> contentsList = myUploadClass.getContentsList();
        contentsList.add(contents1);
        myUploadClass.setContentsList(contentsList);
        myUploadClassRepository.save(myUploadClass);

        return contents1;
    }

    public Page<Contents> getContentsPage(int page, int size) {

        Pageable pageable =
                PageRequest.of(page - 1, size,
                        Sort.by("contentsId").descending());

        return contentsRepository.findAll(pageable);
    }

    // 콘텐츠 찜 기능
    public void wishContents(Long contentsId, Long usersId, Wish wish) {

        Contents contents = contentsRepository.findByContentsId(contentsId);
        MyClass myClass = myClassRepository.findByUsersUsersId(usersId);
        List<Wish> wish1 = wishRepository.findByMyClassMyClassId(myClass.getMyClassId());

        for (Wish wish2 : wish1) {

            if (wish2.getContents() == contents) {

                wishRepository.delete(wish2);
            }

            if (wish2.getContents() == null) {

                wish.setContents(contents);
                wish.setWished(true);
                wishRepository.save(wish);
                myClass.addWish(wish);
                myClassRepository.save(myClass);
                contents.addWish(wish);
                contentsRepository.save(contents);
            }
            else {

                if (wish2.getContents() == contents && wish2.getWished() == true) {

                    Wish wish3 = wishRepository.findByMyClassMyClassIdAndContentsContentsId(myClass.getMyClassId(), contents.getContentsId());

                    List<Wish> wishes = wishRepository.findAll();

                    if (wishes.size() < 2) {
                        Wish wish4 = new Wish();
                        wish4.setMyClass(myClass);
                        wishRepository.save(wish4);
                    }
                    wishRepository.delete(wish3);
                }
                else {

                    wish.setWished(true);
                    wish.setMyClass(myClass);
                    wish.setContents(contents);
                    wishRepository.save(wish);
                    myClass.addWish(wish);
                    myClassRepository.save(myClass);
                    contents.addWish(wish);
                    contentsRepository.save(contents);
                }
            }
        }

//        return "Added to wishlist.";
    }
}
