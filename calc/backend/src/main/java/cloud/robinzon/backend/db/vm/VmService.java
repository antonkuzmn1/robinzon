package cloud.robinzon.backend.db.vm;

import org.springframework.stereotype.Service;

@Service
public class VmService {

    // private VmEntityRepository vmRepository;
    // private FmService fmService;
    // private FmSshService fmSshService;

    // public VmService(
    //         VmEntityRepository vmRepository,
    //         FmService fmService,
    //         FmSshService fmSshService) {
    //     this.vmRepository = vmRepository;
    //     this.fmService = fmService;
    //     this.fmSshService = fmSshService;
    // }

    // private ObjectMapper objectMapper = new ObjectMapper();

    // public List<VmEntity> getAll() {
    //     System.out.println("[VmService][getVm]");
    //     return vmRepository.findAll();
    // }

    // public boolean updateAll() throws JSchException, IOException {
    //     System.out.println("[VmService][updateAll]");
    //     List<FmEntity> fmList = fmService.getByVm(true);
    //     List<VmEntity> vmList = new ArrayList<>();
    //     for (FmEntity fm : fmList)
    //         for (JsonNode json : objectMapper.readTree(fmSshService.get(fm.getIp())))
    //             vmList.add(new VmEntity(
    //                     json.get("vm_id").toString().replace("\"", ""),
    //                     json.get("vm_name").toString().replace("\"", ""),
    //                     json.get("vm_cpu").asInt(),
    //                     json.get("vm_ram").asInt(),
    //                     json.get("vm_ssd").asInt(),
    //                     json.get("vm_hdd").asInt(),
    //                     json.get("vm_state").asBoolean(),
    //                     fm));
    //     vmRepository.saveAll(vmList);
    //     return true;
    // }

}
