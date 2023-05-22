package kz.bitlab.springboot.techboot.repository;

import kz.bitlab.springboot.techboot.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorModelRepository extends JpaRepository<AuthorModel, Long> {

}