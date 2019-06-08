package pl.sda.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.spring.demo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
