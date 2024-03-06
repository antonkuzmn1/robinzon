package cloud.robinzon.backend.db.vm;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcraft.jsch.JSchException;

@RestController
@RequestMapping("db/vm/")
public class VmController {

    private VmService vmService;

    public VmController(
            VmService vmService) {
        this.vmService = vmService;
    }

    @GetMapping("update")
    public boolean updateAll() throws JSchException, IOException {
        return vmService.updateAll();
    }

}
