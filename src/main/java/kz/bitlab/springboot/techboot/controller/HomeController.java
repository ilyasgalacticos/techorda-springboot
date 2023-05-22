package kz.bitlab.springboot.techboot.controller;

import kz.bitlab.springboot.techboot.beans.TestA;
import kz.bitlab.springboot.techboot.model.AuthorModel;
import kz.bitlab.springboot.techboot.model.MusicModel;
import kz.bitlab.springboot.techboot.repository.AuthorModelRepository;
import kz.bitlab.springboot.techboot.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private AuthorModelRepository authorModelRepository;

    @GetMapping(value = "/")
    public String indexPage(Model model,
                            @RequestParam(name = "key", required = false, defaultValue = "") String key){
//        List<MusicModel> musicModelList = musicRepository.findAllByDurationGreaterThanOrderByDurationDesc(0);
        List<MusicModel> musicModelList = musicRepository.searchMusics("%"+key+"%");
        model.addAttribute("muzikalar", musicModelList);
        return "index";
    }

    @PostMapping(value = "/add-music")
    public String addMusic(MusicModel music){
        musicRepository.save(music);
        return "redirect:/";
    }

    @GetMapping(value = "/music-details")
    public String getMusic(@RequestParam(name = "musicId") Long id,
                           Model model){
        MusicModel music = musicRepository.findById(id).orElse(null);
        model.addAttribute("muzyka", music);
        return "details";
    }

    @GetMapping(value = "/add-music")
    public String addMusicPage(Model model){
        List<AuthorModel> authorModelList = authorModelRepository.findAll();
        model.addAttribute("authors", authorModelList);
        return "addmusic";
    }

    @GetMapping(value = "/details/{musicId}")
    public String musicDetails(@PathVariable(name = "musicId") Long id, Model model){

        MusicModel music = musicRepository.findById(id).orElse(null);
        model.addAttribute("muzyka", music);

        List<AuthorModel> authorModelList = authorModelRepository.findAll();
        model.addAttribute("authors", authorModelList);

        return "details";
    }

    @PostMapping(value = "/save-music")
    public String saveMusic(MusicModel music){
        musicRepository.save(music);
        return "redirect:/";
    }

    @PostMapping(value = "/delete-music")
    public String deleteMusic(@RequestParam(name = "id") Long id){
        musicRepository.deleteById(id);
        return "redirect:/";
    }
}