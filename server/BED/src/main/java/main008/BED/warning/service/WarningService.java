package main008.BED.warning.service;

import lombok.RequiredArgsConstructor;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.repository.UploadClassRepository;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import main008.BED.warning.entity.Warning;
import main008.BED.warning.repository.WarningRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WarningService {

    private final WarningRepository warningRepository;
    private final UsersRepository usersRepository;
    private final UploadClassRepository uploadClassRepository;

    /**
     * SAVE: 신고 접수
     */
    public void saveWarning(Warning warning, Long usersId, Long uploadClassId) {

        saveValidation(usersId, uploadClassId); // Validation & Exception Handling

        Users user = usersRepository.findByUsersId(usersId);
        UploadClass uploadClass = uploadClassRepository.findById(uploadClassId).get();

        warning.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        warning.setUsers(user);
        warning.addUploadClass(uploadClass);

        warningRepository.save(warning);

//        List<Warning> warningListInUsers = user.getWarningList();
//        warningListInUsers.add(receivedWarning);
//        user.setWarningList(warningListInUsers);
//
//        List<Warning> warningListInUploadClass = uploadClass.getWarningList();
//        warningListInUploadClass.add(receivedWarning);
//        uploadClass.setWarningList(warningListInUploadClass);
    }

    /**
     * Validation: 신고 접수 예외 처리
     */
    // TODO: 한 유저가 한 챕터의 다른 강의에 대하여 각각 신고를 못하고 있음.
    private void saveValidation(Long usersId, Long uploadClassId) {
        if (!usersRepository.existsById(usersId)) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        } else if (!uploadClassRepository.existsByUploadClassId(uploadClassId)) {
            throw new BusinessLogicException(ExceptionCode.UPLOAD_CLASS_NOT_FOUND);
        }


//        Users user = usersRepository.findByUsersId(usersId);
//        List<Warning> warningList = user.getWarningList();
        List<Warning> warningList = warningRepository.findByUsersId(usersId);
        boolean duplication = warningList.stream()
                .map(warning -> warning.getUploadClass().getUploadClassId())
                .anyMatch(classIdentity -> classIdentity.equals(uploadClassId));

        if (duplication) {
            throw new BusinessLogicException(ExceptionCode.DUPLICATE_WARNING);
        }

    }


//    public List<Warning> findWarningList(Long usersId) {
//        if (!usersRepository.existsById(usersId)) {
//            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
//        }
//
//        Users user = usersRepository.findByUsersId(usersId);
//        List<Warning> warningList = user.getWarningList();
//        return warningList;
//    }

    /**
     * FIND: 사용자 신고 내역 목록 조회 (Version 2: Query)
     */
    public List<Warning> findWarningListByUsersId(Long usersId) {
        if (!usersRepository.existsById(usersId)) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }

        List<Warning> warningListByUsersId = warningRepository.findByUsersId(usersId);
        return warningListByUsersId;
    }
}
