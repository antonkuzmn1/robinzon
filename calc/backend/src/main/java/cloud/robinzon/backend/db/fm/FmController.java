package cloud.robinzon.backend.db.fm;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("db/fm/")
public class FmController {

    private FmService fmService;

    public FmController(
            FmService fmService) {
        this.fmService = fmService;
    }

    @GetMapping
    public List<FmEntity> getAll() {
        return fmService.getAll();
    }

    @GetMapping("test")
    public void test() {
        fmService.test();
    }

}
