package main008.BED.myUploadClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.myUploadClass.repository.MyUploadClassRepository;
import main008.BED.userPage.entity.UserPage;
import main008.BED.users.entity.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MyUploadClassService {

    private final MyUploadClassRepository myUploadClassRepository;
    private final ContentsRepository contentsRepository;

    public void createMyUploadClass(Users users, UserPage userPage) {

        MyUploadClass myUploadClass = new MyUploadClass();
        myUploadClass.setUsers(users);
        myUploadClass.setUserPage(userPage);
        myUploadClass.setContentsList(new ArrayList<>());
        myUploadClassRepository.save(myUploadClass);
    }

    public void setContentsToMyUploadClass(Long usersId, Contents contents) {

        MyUploadClass myUploadClass = myUploadClassRepository.findByUsersId(usersId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.UPLOAD_CLASS_NOT_FOUND));

        List<Contents> contentsList = myUploadClass.getContentsList();

        contentsList.add(contents);
        myUploadClass.setContentsList(contentsList);

        myUploadClassRepository.save(myUploadClass);
    }

    public MyUploadClass getMyUploadClasses(Long usersId) {

        MyUploadClass myUploadClass = myUploadClassRepository.findByUsersId(usersId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.UPLOAD_CLASS_NOT_FOUND));

        List<Contents> contentsList = contentsRepository.findByUsersId(usersId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.CONTENTS_NOT_FOUND));

        myUploadClass.setContentsList(contentsList);

        return myUploadClassRepository.save(myUploadClass);
    }

    public void deleteMyUploadClass(UserPage userPage) {

        MyUploadClass mu = myUploadClassRepository.findByUserPage(userPage.getUserPageId());
        myUploadClassRepository.delete(mu);
    }

}
