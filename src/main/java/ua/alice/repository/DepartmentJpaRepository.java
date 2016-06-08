package ua.alice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.alice.entity.Department;

public interface DepartmentJpaRepository extends JpaRepository<Department,Integer>{
}
