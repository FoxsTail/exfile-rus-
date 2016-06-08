package ua.alice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.alice.entity.ExFile;

/**
 * Created by Лис on 30.05.2016.
 */
public interface ExFileJpaRepository extends JpaRepository<ExFile, Long> {
}
