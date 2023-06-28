package kz.bitlab.springboot.techboot.service;

import kz.bitlab.springboot.techboot.model.AuthorModel;
import kz.bitlab.springboot.techboot.repository.AuthorModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorModelRepository authorModelRepository;

    public List<AuthorModel> getAuthors(){
        return authorModelRepository.findAll();
    }

}
