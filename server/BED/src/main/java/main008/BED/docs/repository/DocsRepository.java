package main008.BED.docs.repository;

import main008.BED.docs.entity.Docs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocsRepository extends JpaRepository<Docs, Long> {

//    Docs findByName(String name);

    boolean existsByName(String name);

    boolean existsById(Long id);

}
