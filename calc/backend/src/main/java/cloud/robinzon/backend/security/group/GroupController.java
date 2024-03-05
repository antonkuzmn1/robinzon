package cloud.robinzon.backend.security.group;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security/group/")
public class GroupController {

    private GroupService groupService;

    public GroupController(
            GroupService groupService) {
        this.groupService = groupService;
    }

}
