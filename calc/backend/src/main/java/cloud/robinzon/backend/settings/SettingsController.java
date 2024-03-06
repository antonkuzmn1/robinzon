package cloud.robinzon.backend.settings;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("settings/")
public class SettingsController {

    private SettingsService settingsService;

    public SettingsController(
            SettingsService settingsService) {
        this.settingsService = settingsService;
    }

}
