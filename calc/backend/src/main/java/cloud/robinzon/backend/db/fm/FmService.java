package cloud.robinzon.backend.db.fm;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FmService {

    private FmEntityRepository fmRepository;

    public FmService(
            FmEntityRepository fmRepository) {
        this.fmRepository = fmRepository;
    }

    public List<FmEntity> getAll() {
        System.out.println("[FmService][getAll]");
        return fmRepository.findAll();
    }

    public List<FmEntity> getByVm(Boolean vm) {
        System.out.println("[FmService][getByVm]");
        return fmRepository.findByVm(vm);
    }

    public void test() {
        System.out.println("[FmService][test]");
        fmRepository.save(new FmEntity(
                "TEST",
                "000.000.000.000",
                "",
                "",
                "",
                0,
                false,
                null));
    }

}
