package main008.BED.upload_class.repository;

import main008.BED.upload_class.entity.UploadClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadClassRepository extends JpaRepository<UploadClass, Long> {

}
