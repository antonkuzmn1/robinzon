package cloud.robinzon.backend.security.group;

import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private GroupEntityRepository groupEntityRepository;

    public GroupService(
            GroupEntityRepository groupEntityRepository) {
        this.groupEntityRepository = groupEntityRepository;
    }

}
