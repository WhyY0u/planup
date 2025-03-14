package cc.whyy0u.service.target;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cc.whyy0u.entity.target.TargetEntity;
import cc.whyy0u.repository.target.TargetRepository;
import cc.whyy0u.utils.TargetResponseUtils;

@Service
public class TargetService {

    @Autowired
    TargetRepository targetRepository;

    public void save(TargetEntity entity) {
        targetRepository.save(entity);
    }
    public TargetEntity getTargetByID(Long id) {
        return targetRepository.findById(id).orElse(null);
    }

     public Page<TargetEntity> getTargetsByUser(Long userId, Pageable pageable) {
        return targetRepository.findByCreatorId(userId, pageable);
    }

    public Page<TargetResponseUtils> convertToTargetResponse(Page<TargetEntity> targetEntities) {
        return targetEntities.map(targetEntity -> new TargetResponseUtils(targetEntity));
    }
    
}
