package co.ahmoh.newsapp.repositories;

import co.ahmoh.newsapp.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    Optional<News> findByIdAndAdminId(Long id, Long adminId);

}
