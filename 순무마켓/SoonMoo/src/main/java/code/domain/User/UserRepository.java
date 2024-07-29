package code.domain.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>
{
    @Query(value = "SELECT * FROM user WHERE user_id=:id", nativeQuery = true)
    public Optional<UserEntity> findByUserId(@Param("id") String id);

    @Query(value = "SELECT * FROM user WHERE user_id=:id", nativeQuery = true)
    public List<UserEntity> findAllByUserId(@Param("id") String id);

}