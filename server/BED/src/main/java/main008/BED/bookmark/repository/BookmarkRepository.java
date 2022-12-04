package main008.BED.bookmark.repository;

import main008.BED.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query(value = "SELECT bookmark " +
            "FROM Bookmark  bookmark " +
            "WHERE bookmark.users.usersId = :usersId " +
            "AND bookmark.uploadClass.uploadClassId = :uploadClassId")
    List<Bookmark> findByUsersIdAndUploadClassId(@Param("usersId") Long usersId,
                                               @Param("uploadClassId") Long uploadClassId);
}
