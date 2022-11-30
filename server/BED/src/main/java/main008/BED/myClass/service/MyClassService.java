package main008.BED.myClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myClass.repository.MyClassRepository;
import main008.BED.payment.service.PaymentService;
import main008.BED.users.entity.Users;
import main008.BED.wish.entity.Wish;
import main008.BED.wish.service.WishService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
public class MyClassService {

    private final MyClassRepository myClassRepository;
    private final PaymentService paymentService;
    private final WishService wishService;

    public void createMyClass(Users users) {

        MyClass myClass = new MyClass();
        myClass.setWishes(new ArrayList<>());
        myClass.setPayments(new ArrayList<>());
        myClass.setUsers(users);

        myClass.addWish(wishService.createWish(myClass));

        myClassRepository.save(myClass);
    }

    /*
    내가 찜한 컨텐츠
    */
    @Transactional(readOnly = true)
    public MyClass getWishClass(Long usersId) {

        MyClass myClass = myClassRepository.findByUsersId(usersId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        myClass.setWishes(wishService.findTrueWishes(myClass.getMyClassId()));

        return myClassRepository.save(myClass);
    }

    /*
    내가 구매한 컨텐츠 (수강중인 컨텐츠)
    */
    @Transactional(readOnly = true)
    public MyClass getBuyClass(Long usersId) {

        MyClass myClass = myClassRepository.findByUsersId(usersId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        myClass.setPayments(paymentService.getPayContent(usersId, myClass));

        return myClassRepository.save(myClass);
    }

    public void deleteMyClass(Users users) {

        MyClass myClass = myClassRepository.findByUsersId(users.getUsersId()).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        myClassRepository.delete(myClass);
    }

    public MyClass findMyClass(Long usersId) {

        return myClassRepository.findByUsersId(usersId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    public void setWishForMyClass(Wish wish, MyClass myClass) {

        myClass.addWish(wish);
        myClassRepository.save(myClass);
    }
}
