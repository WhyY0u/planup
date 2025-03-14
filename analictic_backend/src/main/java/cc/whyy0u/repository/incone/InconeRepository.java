package cc.whyy0u.repository.incone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cc.whyy0u.entity.incone.InconeEntity;

public interface InconeRepository extends CrudRepository<InconeEntity, Long> {

     @Query("SELECT t FROM InconeEntity t WHERE t.creator_id = :creator_id")
     Page<InconeEntity> findByCreatorId(@Param("creator_id") Long creator_id, Pageable pageable);
     
}
