package cloud.robinzon.backend.db.vm;

import org.springframework.stereotype.Service;

import cloud.robinzon.backend.ResponseForm;
import cloud.robinzon.backend.db.fm.FmEntity;
import cloud.robinzon.backend.db.fm.FmEntityRepository;

@Service
public class VmEntityManager {

    private VmEntityRepository entityRepository;
    private VmHistoryRepository historyRepository;
    private VmRentRepository rentRepository;
    private FmEntityRepository fmEntityRepository;

    public VmEntityManager(
            VmEntityRepository entityRepository,
            VmHistoryRepository historyRepository,
            VmRentRepository rentRepository,
            FmEntityRepository fmEntityRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
        this.rentRepository = rentRepository;
        this.fmEntityRepository = fmEntityRepository;
    }

    private ResponseForm responseForm = new ResponseForm("VmEntityManager");

    public ResponseForm insert(
            String id,
            String name,
            int cpu,
            int ram,
            int ssd,
            int hdd,
            boolean running,
            FmEntity fmEntity) {
        responseForm.function("insert");

        if (id == null)
            return responseForm.error("ID cannot be null");
        if (id.length() > 36)
            return responseForm.error("ID cannot contain more than 36 characters");
        if (name == null)
            return responseForm.error("Name cannot be null");
        if (name.length() > 100)
            return responseForm.error("Name cannot contain more than 100 characters");
        if (cpu < 1)
            return responseForm.error("CPU cannot be less than 1");
        if (cpu > 64)
            return responseForm.error("CPU cannot be more than 64");
        if (ram < 0)
            return responseForm.error("RAM cannot be less than 0");
        if (ram > 256)
            return responseForm.error("RAM cannot be more than 256");
        if (ssd < 0)
            return responseForm.error("SSD cannot be less than 0");
        if (ssd > 10000)
            return responseForm.error("SSD cannot be more than 10000");
        if (hdd < 0)
            return responseForm.error("HDD cannot be less than 0");
        if (hdd > 50000)
            return responseForm.error("HDD cannot be more than 50000");
        if (fmEntity == null)
            return responseForm.error("FM cannot be null");
        if (fmEntityRepository.findById(fmEntity.getId()).orElse(null) == null)
            return responseForm.error("FM with ID " + fmEntity.getId() + " not found");

        VmEntity entity = entityRepository.findById(id).orElse(null);

        if (entity == null) {

            VmEntity entityNew = new VmEntity(
                    id,
                    name,
                    cpu,
                    ram,
                    ssd,
                    hdd,
                    running,
                    fmEntity);
            entityRepository.save(entity);

            historyRepository.save(new VmHistory(
                    entityNew,
                    name,
                    cpu,
                    ram,
                    ssd,
                    hdd,
                    running,
                    "",
                    "",
                    fmEntity,
                    null, // no need cuz it change by system
                    false));

            rentRepository.save(new VmRent(
                    entityNew,
                    null,
                    null));

            return responseForm.success("Inserted new VM: " + entityNew.getName());
        }

        if (entity.getName().equals(name)
                && entity.getCpu() == cpu
                && entity.getRam() == ram
                && entity.getSsd() == ssd
                && entity.getHdd() == hdd
                && entity.isRunning() == running
                && entity.getFmEntity().equals(fmEntity))
            return responseForm.error("All params of " + entity.getName() + " is equal");

        entity.setName(name);
        entity.setCpu(cpu);
        entity.setRam(ram);
        entity.setSsd(ssd);
        entity.setHdd(hdd);
        entity.setRunning(running);
        entity.setFmEntity(fmEntity);
        entityRepository.save(entity);

        historyRepository.save(new VmHistory(
                entity,
                name,
                cpu,
                ram,
                ssd,
                hdd,
                running,
                entity.getTitle(),
                entity.getDescription(),
                fmEntity,
                null, // no need cuz it change by system
                false));

        return responseForm.success("Updated VM: " + entity.getName());
    }

    public ResponseForm update(
            String id,
            String title,
            String description) {
        responseForm.function("update");

        if (id == null)
            return responseForm.error("ID cannot be null");
        if (id.length() > 36)
            return responseForm.error("ID cannot contain more than 36 characters");
        if (title == null)
            title = "";
        if (title.length() > 100)
            return responseForm.error("Title cannot contain more than 100 characters");
        if (description == null)
            description = "";
        if (description.length() > 255)
            return responseForm.error("Description cannot contain more than 255 characters");

        VmEntity entity = entityRepository.findById(id).orElse(null);

        if (entity == null)
            return responseForm.error("VM with ID " + id + " not found");

        if (entity.getTitle().equals(title)
                && entity.getDescription().equals(description))
            return responseForm.error("All params of " + entity.getName() + " is equal");

        entity.setTitle(title);
        entity.setDescription(description);
        entityRepository.save(entity);

        historyRepository.save(new VmHistory(
                entity,
                id,
                entity.getCpu(),
                entity.getRam(),
                entity.getSsd(),
                entity.getHdd(),
                entity.isRunning(),
                title,
                description,
                entity.getFmEntity(),
                null, // spring security system required
                false));

        return responseForm.success("Updated VM: " + entity.getName());
    }

    public ResponseForm delete() {
        responseForm.function("delete");
        return responseForm.error("Function not working");
    }

}
