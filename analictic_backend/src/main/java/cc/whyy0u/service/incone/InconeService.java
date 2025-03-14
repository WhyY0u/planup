package cc.whyy0u.service.incone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cc.whyy0u.entity.incone.InconeEntity;
import cc.whyy0u.repository.incone.InconeRepository;

@Service
public class InconeService {

    @Autowired
    InconeRepository inconeRepository;


     public Page<InconeEntity> getTargetsByUser(Long userId, Pageable pageable) { 
        return inconeRepository.findByCreatorId(userId, pageable);
     }
     
     public InconeEntity findInconeByID(Long id) {
        return inconeRepository.findById(id).orElse(null);
     }

     public void save(InconeEntity inconeEntity) {
        inconeRepository.save(inconeEntity);
     }
    
}
