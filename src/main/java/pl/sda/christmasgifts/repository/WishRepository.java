package pl.sda.christmasgifts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.christmasgifts.entity.Wish;
@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
}
