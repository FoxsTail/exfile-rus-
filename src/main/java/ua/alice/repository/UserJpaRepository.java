package ua.alice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.alice.entity.User;

/**
 * Created by Лис on 26.05.2016.
 */
public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findUserByLogin(String login);
}
