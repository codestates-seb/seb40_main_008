package main008.BED.myUploadClass.repository;

import main008.BED.myUploadClass.entity.MyUploadClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUploadClassRepository extends JpaRepository<MyUploadClass, Long> {
}
