package ua.alice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.alice.entity.Subdivision;

/**
 * Created by Лис on 27.05.2016.
 */
public interface SubdivisionJpaRepository extends JpaRepository<Subdivision, Integer>{
}
