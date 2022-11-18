package main008.BED.myUploadClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
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

        MyUploadClass myUploadClass = myUploadClassRepository.findByUsersUsersId(usersId);

        List<Contents> contentsList = contentsRepository.findByUsersUsersId(usersId);
        myUploadClass.setContentsList(contentsList);

        return myUploadClassRepository.save(myUploadClass);
    }

}
