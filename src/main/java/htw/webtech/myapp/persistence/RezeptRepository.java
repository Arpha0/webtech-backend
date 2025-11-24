package htw.webtech.myapp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezeptRepository extends JpaRepository<Rezept, Long> {
}