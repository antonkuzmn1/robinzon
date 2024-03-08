package cloud.robinzon.backend.db.vm;

import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;

import cloud.robinzon.backend.db.client.ClientEntity;
import cloud.robinzon.backend.db.fm.FmEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vm_entity")
@EntityListeners(VmEntityListener.class)
public class VmEntity {

    @Id
    @Column(length = 36)
    private String id;

    @UpdateTimestamp
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
    @JoinColumn
    private ClientEntity clientEntity;

    @Column(nullable = false)
    private boolean deleted;

    public VmEntity(
            String id,
            String name,
            int cpu,
            int ram,
            int ssd,
            int hdd,
            boolean running,
            FmEntity fmEntity) {
        this.id = id;
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
        this.hdd = hdd;
        this.running = running;
        this.fmEntity = fmEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "VmEntity [id=" + id
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
                + ", clientEntity=" + clientEntity
                + ", deleted=" + deleted
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        result = prime * result + ((clientEntity == null) ? 0 : clientEntity.hashCode());
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
        VmEntity other = (VmEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
        if (clientEntity == null) {
            if (other.clientEntity != null)
                return false;
        } else if (!clientEntity.equals(other.clientEntity))
            return false;
        if (deleted != other.deleted)
            return false;
        return true;
    }

}