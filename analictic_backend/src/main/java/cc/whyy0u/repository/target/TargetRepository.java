package cc.whyy0u.repository.target;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cc.whyy0u.entity.target.TargetEntity;

public interface TargetRepository extends CrudRepository<TargetEntity, Long> {

     @Query("SELECT t FROM TargetEntity t WHERE t.creator_id = :creator_id")
     Page<TargetEntity> findByCreatorId(@Param("creator_id") Long creator_id, Pageable pageable);

} 
