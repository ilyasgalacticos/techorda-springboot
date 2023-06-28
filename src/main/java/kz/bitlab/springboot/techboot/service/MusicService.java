package kz.bitlab.springboot.techboot.service;


import kz.bitlab.springboot.techboot.model.GenreModel;
import kz.bitlab.springboot.techboot.model.MusicModel;
import kz.bitlab.springboot.techboot.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;
    private final GenreService genreService;

    public List<MusicModel> searchMusic(String key){
        List<MusicModel> musicModelList = musicRepository.searchMusics("%" + key + "%");
        return musicModelList;
    }

    public MusicModel addMusic(MusicModel music){
        return musicRepository.save(music);
    }

    public MusicModel getMusic(Long id){
        return musicRepository.findById(id).orElse(null);
    }

    public MusicModel saveMusic(MusicModel music){
        return musicRepository.save(music);
    }

    public void assignGenre(Long musicId, Long genreId){

        MusicModel musicModel = getMusic(musicId);
        GenreModel genreModel = genreService.getGenre(genreId);

        if (musicModel.getGenres() != null && musicModel.getGenres().size() > 0) {
            if (!musicModel.getGenres().contains(genreModel)) {
                musicModel.getGenres().add(genreModel);
            }
        } else {
            List<GenreModel> genreModelList = new ArrayList<>();
            genreModelList.add(genreModel);
            musicModel.setGenres(genreModelList);
        }
        musicRepository.save(musicModel);
    }

    public void unassignGenre(Long musicId, Long genreId){
        MusicModel musicModel = musicRepository.findById(musicId).orElseThrow();
        GenreModel genreModel = genreService.getGenre(genreId);

        if (musicModel.getGenres() != null && musicModel.getGenres().size() > 0) {
            musicModel.getGenres().remove(genreModel);
        }
        musicRepository.save(musicModel);
    }

    public void delete(Long id){
        musicRepository.deleteById(id);
    }

}
