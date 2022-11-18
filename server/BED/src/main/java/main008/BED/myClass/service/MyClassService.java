package main008.BED.myClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myClass.repository.MyClassRepository;
import main008.BED.wish.entity.Wish;
import main008.BED.wish.repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MyClassService {

    private final MyClassRepository myClassRepository;
    private final WishRepository wishRepository;

    public MyClass getWishClass(Long usersId) {

        MyClass myClass = myClassRepository.findByUsersUsersId(usersId);
        List<Wish> wishes = wishRepository.findByMyClassMyClassIdAndWishedTrue(myClass.getMyClassId());

        myClass.setWishes(wishes);

        return myClassRepository.save(myClass);
    }
}
