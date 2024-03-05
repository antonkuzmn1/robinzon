package cloud.robinzon.backend.db.vm;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcraft.jsch.JSchException;

@RestController
@RequestMapping("db/vm/")
public class VmController {

    private VmService service;

    public VmController(
            VmService service) {
        this.service = service;
    }

    @GetMapping
    public List<VmEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("update")
    public Boolean updateAll() throws JSchException, IOException {
        return service.updateAll();
    }
}
