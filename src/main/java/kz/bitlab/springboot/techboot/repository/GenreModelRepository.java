package kz.bitlab.springboot.techboot.repository;

import kz.bitlab.springboot.techboot.model.GenreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreModelRepository extends JpaRepository<GenreModel, Long> {

}
