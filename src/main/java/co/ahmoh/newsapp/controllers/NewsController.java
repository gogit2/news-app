package co.ahmoh.newsapp.controllers;
import co.ahmoh.newsapp.entities.News;
import co.ahmoh.newsapp.files.FileUploadUtil;
import co.ahmoh.newsapp.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/new")
    public String addNewNews(Model model){
        News theNew = new News();
        model.addAttribute("theNews", theNew);
        return "news-form";
    }

    @PostMapping("/save")
    public String saveNews(News theNews, RedirectAttributes redirectAttributes,
                           @RequestParam("user-image") MultipartFile multipartFile) throws IOException {

        if(!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            theNews.setImage(fileName);
            News savedNews = newsService.saveNews(theNews);
            String uploadDir = "news-photos/" + savedNews.getId();
            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            if (theNews.getImage().isEmpty())
                theNews.setImage(null);
            newsService.saveNews(theNews);
        }
        return "redirect:/news/all";
    }

    @GetMapping("/all")
    public String listAllNews(Model model){
        List<News> listNews = newsService.getAllNews();

        model.addAttribute("listAllNews", listNews);

        return "news";
    }

}

