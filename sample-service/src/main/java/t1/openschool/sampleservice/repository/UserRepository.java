package t1.openschool.sampleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t1.openschool.sampleservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
