package cc.whyy0u.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cc.whyy0u.controller.user.request.AddTargetRequest;
import cc.whyy0u.controller.user.request.UpadateTargetRequest;
import cc.whyy0u.entity.target.TargetEntity;
import cc.whyy0u.entity.user.UserEntity;
import cc.whyy0u.service.target.TargetService;
import cc.whyy0u.service.user.UserService;
import cc.whyy0u.utils.TargetResponseUtils;


@Controller
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TargetService targetService;


    @PostMapping("/addtarget")
    public ResponseEntity<?> addTarget(@RequestBody AddTargetRequest request) {    
        UserEntity user = userService.getCurrentUser();
        TargetEntity entity = new TargetEntity(request, user.getId());
        targetService.save(entity);
        return ResponseEntity.ok("save");
    }

    @GetMapping("/targets")
    public ResponseEntity<?> getTargets(
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "10") int size) {
         UserEntity user = userService.getCurrentUser();
         Pageable pageable = PageRequest.of(page, size);
         Page<TargetResponseUtils> targets =  targetService.convertToTargetResponse(targetService.getTargetsByUser(user.getId(), pageable));
        return ResponseEntity.ok(targets);
    }

    @GetMapping("/target/{id}")
    public ResponseEntity<?> getTargets(@PathVariable Long id) {    
        UserEntity user = userService.getCurrentUser();
        TargetEntity entity = targetService.getTargetByID(id);
        if(!user.getId().equals(entity.getCreator_id())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/target/{id}/add")
    public ResponseEntity<?> updateTarget(@PathVariable Long id, @RequestBody UpadateTargetRequest updateTarget) {
        UserEntity user = userService.getCurrentUser();
        TargetEntity entity = targetService.getTargetByID(id);
        if(!user.getId().equals(entity.getCreator_id())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        if(updateTarget.getCurrntPrice() != -1) {
            if(updateTarget.getCurrntPrice() + entity.getCurntPrice() > entity.getTargetPrice()) return ResponseEntity.badRequest().body("Текущий прогресс не может быть больше максимального прогресса");
            entity.setCurntPrice(entity.getCurntPrice() + updateTarget.getCurrntPrice());
        }
        if(updateTarget.getPhoto() != null) entity.setPhoto(updateTarget.getPhoto());
        if(updateTarget.getName() != null) entity.setName(updateTarget.getName());
     return ResponseEntity.ok(updateTarget);

    }
    
    
    
}
