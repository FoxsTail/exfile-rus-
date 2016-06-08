package ua.alice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.alice.entity.Category;

/**
 * Created by Лис on 27.05.2016.
 */
public interface CategoryJpaRepository extends JpaRepository<Category, Integer> {
}
