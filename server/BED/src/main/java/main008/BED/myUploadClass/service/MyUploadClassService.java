package main008.BED.myUploadClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.myUploadClass.repository.MyUploadClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MyUploadClassService {

    private final MyUploadClassRepository myUploadClassRepository;
    private final ContentsRepository contentsRepository;

    public MyUploadClass getMyUploadClasses(Long usersId) {

        MyUploadClass myUploadClass = myUploadClassRepository.findByUsersId(usersId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.UPLOAD_CLASS_NOT_FOUND));

        List<Contents> contentsList = contentsRepository.findByUsersId(usersId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.CONTENTS_NOT_FOUND));

        myUploadClass.setContentsList(contentsList);

        return myUploadClassRepository.save(myUploadClass);
    }

}
