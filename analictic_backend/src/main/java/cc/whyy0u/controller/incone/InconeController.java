package cc.whyy0u.controller.incone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.whyy0u.controller.incone.request.AddInconeRequest;
import cc.whyy0u.entity.incone.InconeEntity;
import cc.whyy0u.entity.user.UserEntity;
import cc.whyy0u.service.incone.InconeService;
import cc.whyy0u.service.user.UserService;


@Controller
@RequestMapping("/api/incone")
public class InconeController {

    @Autowired
    UserService userService;

    @Autowired
    InconeService inconeService;

    @PostMapping("/get/{id}")
    public ResponseEntity<?> getIncone(@PathVariable Long id) {
        UserEntity user = userService.getCurrentUser();
        InconeEntity inconeEntity = inconeService.findInconeByID(id);
        if(!user.getId().equals(inconeEntity.getId())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(inconeEntity);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addIncone(@RequestBody AddInconeRequest request) {
        UserEntity user = userService.getCurrentUser();
        InconeEntity inconeEntity = new InconeEntity();
        inconeEntity.setAmount(request.getAmount());
        inconeEntity.setCreator_id(user.getId());
        inconeEntity.setName(request.getName());
        inconeEntity.setNextDate(request.getNextDate());
        inconeService.save(inconeEntity);
        return ResponseEntity.ok("ok");
    }
    
    
}
