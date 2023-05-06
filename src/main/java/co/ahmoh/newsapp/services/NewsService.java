package co.ahmoh.newsapp.services;

import co.ahmoh.newsapp.entities.News;
import co.ahmoh.newsapp.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public Optional<News> getNewsByIdAndAdminId(Long id, Long adminId) {
        return newsRepository.findByIdAndAdminId(id, adminId);
    }

    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    public void deleteNewsById(Long id) {
        newsRepository.deleteById(id);
    }

    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }
}
