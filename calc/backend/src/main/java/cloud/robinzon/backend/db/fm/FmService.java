package cloud.robinzon.backend.db.fm;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FmService {

    private FmEntityRepository fmRepository;
    private FmHistoryRepository fmHistoryRepository;
    private FmRentRepository fmRentRepository;

    public FmService(
            FmEntityRepository fmRepository,
            FmHistoryRepository fmHistoryRepository,
            FmRentRepository fmRentRepository) {
        this.fmRepository = fmRepository;
        this.fmHistoryRepository = fmHistoryRepository;
        this.fmRentRepository = fmRentRepository;
    }

    public List<FmEntity> getAll() {
        System.out.println("[FmService][getAll]");
        return fmRepository.findAll();
    }

    public List<FmHistory> getHistoryAll() {
        System.out.println("[FmService][getHistoryAll]");
        return fmHistoryRepository.findAll();
    }

    public List<FmRent> getRentAll() {
        System.out.println("[FmService][getRentAll]");
        return fmRentRepository.findAll();
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
