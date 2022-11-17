package main008.BED.myClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myClass.repository.MyClassRepository;
import main008.BED.wish.dto.WishDto;
import main008.BED.wish.entity.Wish;
import main008.BED.wish.mapper.WishMapper;
import main008.BED.wish.repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MyClassService {

    private final MyClassRepository myClassRepository;

    public MyClass getWishClass(Long usersId) {

        return myClassRepository.findByUsersUsersId(usersId);
    }
}
