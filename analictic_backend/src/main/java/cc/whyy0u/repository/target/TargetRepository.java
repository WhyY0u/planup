package cc.whyy0u.repository.target;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import cc.whyy0u.entity.target.TargetEntity;

public interface TargetRepository extends CrudRepository<TargetEntity, Long> {

     Optional<ArrayList<TargetEntity>> findAllTargetByUser(Long userid);
     Page<TargetEntity> findByCreatorId(Long creatorId, Pageable pageable);

} 
