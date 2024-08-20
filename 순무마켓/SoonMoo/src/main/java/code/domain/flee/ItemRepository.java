package code.domain.flee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>
{
    // 
    @Query(value="SELECT * FROM item", nativeQuery = true)
    public Page<ItemEntity> pageOf(Pageable page);
}
