//package main008.BED.wish.wishService;
//
//import lombok.RequiredArgsConstructor;
//import main008.BED.wish.entity.Wish;
//import main008.BED.wish.repository.WishRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class WishService {
//
//    private final WishRepository wishRepository;
//
//    public List<Wish> getWishClassList(Long myClassId) {
//
//        return wishRepository.findByWishedTrueAndMyClassMyClassId(myClassId);
//    }
//}
