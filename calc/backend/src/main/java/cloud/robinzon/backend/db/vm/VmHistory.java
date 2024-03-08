package cloud.robinzon.backend.db.vm;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import cloud.robinzon.backend.db.fm.FmEntity;
import cloud.robinzon.backend.security.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vm_history")
@IdClass(VmHistoryKey.class)
public class VmHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private VmEntity vmEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int cpu;

    @Column(nullable = false)
    private int ram;

    @Column(nullable = false)
    private int ssd;

    @Column(nullable = false)
    private int hdd;

    @Column(nullable = false)
    private boolean running;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FmEntity fmEntity;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    public VmHistory(
            VmEntity vmEntity,
            String name,
            Integer cpu,
            Integer ram,
            Integer ssd,
            Integer hdd,
            Boolean running,
            String title,
            String description,
            FmEntity fmEntity,
            UserEntity changeBy,
            boolean deleted) {
        this.vmEntity = vmEntity;
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
        this.hdd = hdd;
        this.running = running;
        this.title = title;
        this.description = description;
        this.fmEntity = fmEntity;
        this.changeBy = changeBy;
        this.deleted = deleted;
    }

    public VmEntity getVmEntity() {
        return vmEntity;
    }

    public void setVmEntity(VmEntity vmEntity) {
        this.vmEntity = vmEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getSsd() {
        return ssd;
    }

    public void setSsd(int ssd) {
        this.ssd = ssd;
    }

    public int getHdd() {
        return hdd;
    }

    public void setHdd(int hdd) {
        this.hdd = hdd;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FmEntity getFmEntity() {
        return fmEntity;
    }

    public void setFmEntity(FmEntity fmEntity) {
        this.fmEntity = fmEntity;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(UserEntity changeBy) {
        this.changeBy = changeBy;
    }

    @Override
    public String toString() {
        return "VmHistory [vmEntity=" + vmEntity
                + ", timestamp=" + timestamp
                + ", name=" + name
                + ", cpu=" + cpu
                + ", ram=" + ram
                + ", ssd=" + ssd
                + ", hdd=" + hdd
                + ", running=" + running
                + ", title=" + title
                + ", description=" + description
                + ", fmEntity=" + fmEntity
                + ", changeBy=" + changeBy
                + ", deleted=" + deleted
                + "]";
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vmEntity == null) ? 0 : vmEntity.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + cpu;
        result = prime * result + ram;
        result = prime * result + ssd;
        result = prime * result + hdd;
        result = prime * result + (running ? 1231 : 1237);
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((fmEntity == null) ? 0 : fmEntity.hashCode());
        result = prime * result + ((changeBy == null) ? 0 : changeBy.hashCode());
        result = prime * result + (deleted ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VmHistory other = (VmHistory) obj;
        if (vmEntity == null) {
            if (other.vmEntity != null)
                return false;
        } else if (!vmEntity.equals(other.vmEntity))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (cpu != other.cpu)
            return false;
        if (ram != other.ram)
            return false;
        if (ssd != other.ssd)
            return false;
        if (hdd != other.hdd)
            return false;
        if (running != other.running)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (fmEntity == null) {
            if (other.fmEntity != null)
                return false;
        } else if (!fmEntity.equals(other.fmEntity))
            return false;
        if (changeBy == null) {
            if (other.changeBy != null)
                return false;
        } else if (!changeBy.equals(other.changeBy))
            return false;
        if (deleted != other.deleted)
            return false;
        return true;
    }

}