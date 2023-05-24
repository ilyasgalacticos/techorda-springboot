package kz.bitlab.springboot.techboot.controller;

import kz.bitlab.springboot.techboot.beans.TestA;
import kz.bitlab.springboot.techboot.model.AuthorModel;
import kz.bitlab.springboot.techboot.model.GenreModel;
import kz.bitlab.springboot.techboot.model.MusicModel;
import kz.bitlab.springboot.techboot.repository.AuthorModelRepository;
import kz.bitlab.springboot.techboot.repository.GenreModelRepository;
import kz.bitlab.springboot.techboot.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MusicRepository musicRepository;
    private final AuthorModelRepository authorModelRepository;
    private final GenreModelRepository genreModelRepository;


    @GetMapping(value = "/")
    public String indexPage(Model model,
                            @RequestParam(name = "key", required = false, defaultValue = "") String key) {
//        List<MusicModel> musicModelList = musicRepository.findAllByDurationGreaterThanOrderByDurationDesc(0);
        List<MusicModel> musicModelList = musicRepository.searchMusics("%" + key + "%");
        model.addAttribute("muzikalar", musicModelList);
        return "index";
    }

    @PostMapping(value = "/add-music")
    public String addMusic(MusicModel music) {
        musicRepository.save(music);
        return "redirect:/";
    }

    @GetMapping(value = "/add-music")
    public String addMusicPage(Model model) {
        List<AuthorModel> authorModelList = authorModelRepository.findAll();
        model.addAttribute("authors", authorModelList);
        return "addmusic";
    }

    @GetMapping(value = "/details/{musicId}")
    public String musicDetails(@PathVariable(name = "musicId") Long id, Model model) {

        MusicModel music = musicRepository.findById(id).orElse(null);
        model.addAttribute("muzyka", music);

        List<AuthorModel> authorModelList = authorModelRepository.findAll();
        model.addAttribute("authors", authorModelList);

        List<GenreModel> genreModelList = genreModelRepository.findAll();
        genreModelList.removeAll(music.getGenres());
        model.addAttribute("genres", genreModelList);

        return "details";
    }

    @PostMapping(value = "/save-music")
    public String saveMusic(MusicModel music) {
        musicRepository.save(music);
        return "redirect:/";
    }

    @PostMapping(value = "/delete-music")
    public String deleteMusic(@RequestParam(name = "id") Long id) {
        musicRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping(value = "/assign-genre")
    public String assignGenre(@RequestParam(name = "music_id") Long musicId,
                              @RequestParam(name = "genre_id") Long genreId) {

        MusicModel musicModel = musicRepository.findById(musicId).orElseThrow();
        GenreModel genreModel = genreModelRepository.findById(genreId).orElseThrow();

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
        return "redirect:/details/" + musicId;
    }

    @PostMapping(value = "/unassign-genre")
    public String unassignGenre(@RequestParam(name = "music_id") Long musicId,
                                @RequestParam(name = "genre_id") Long genreId) {

        MusicModel musicModel = musicRepository.findById(musicId).orElseThrow();
        GenreModel genreModel = genreModelRepository.findById(genreId).orElseThrow();

        if (musicModel.getGenres() != null && musicModel.getGenres().size() > 0) {
            musicModel.getGenres().remove(genreModel);
        }

        musicRepository.save(musicModel);
        return "redirect:/details/" + musicId;
    }
}