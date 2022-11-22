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

        saveValidation(usersId); // Validation & Exception Handling

        Users user = usersRepository.findByUsersId(usersId);
        UploadClass uploadClass = uploadClassRepository.findById(uploadClassId).get();

        warning.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        warning.setUsers(user);
        warning.setUploadClass(uploadClass);

        Warning receivedWarning = warningRepository.save(warning);

        List<Warning> warningListInUsers = user.getWarningList();
        warningListInUsers.add(receivedWarning);
        user.setWarningList(warningListInUsers);

        List<Warning> warningListInUploadClass = uploadClass.getWarningList();
        warningListInUploadClass.add(receivedWarning);
        uploadClass.setWarningList(warningListInUploadClass);
    }

    /**
     * Validation: 신고 접수 예외 처리
     */
    private void saveValidation(Long usersId) {
        if (!usersRepository.existsById(usersId)) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }

        // 같은 유저가 한 강의 당 한 번의 신고만 가능

        List<Warning> warningCollect = warningRepository.findAll();
        boolean duplication = warningCollect.stream()
                .map(warningElem -> warningElem.getUsers().getUsersId())
                .anyMatch(userIdentity -> userIdentity.equals(usersId));
        if (duplication) {
            throw new BusinessLogicException(ExceptionCode.DUPLICATE_WARNING);
        }
    }
}
