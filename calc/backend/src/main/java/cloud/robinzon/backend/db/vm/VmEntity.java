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

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FmEntity fm;

    @ManyToOne
    @JoinColumn
    private ClientEntity client;

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
            String title,
            String description,
            FmEntity fm,
            ClientEntity client,
            boolean deleted) {
        this.id = id;
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
        this.hdd = hdd;
        this.running = running;
        this.title = title;
        this.description = description;
        this.fm = fm;
        this.client = client;
        this.deleted = deleted;
    }

    public VmEntity(
            String id,
            String name,
            int cpu,
            int ram,
            int ssd,
            int hdd,
            boolean running,
            FmEntity fm) {
        this.id = id;
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
        this.hdd = hdd;
        this.running = running;
        this.fm = fm;
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

    public FmEntity getFm() {
        return fm;
    }

    public void setFm(FmEntity fm) {
        this.fm = fm;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
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
                + ", fm=" + fm
                + ", client=" + client
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
        result = prime * result + ((fm == null) ? 0 : fm.hashCode());
        result = prime * result + ((client == null) ? 0 : client.hashCode());
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
        if (fm == null) {
            if (other.fm != null)
                return false;
        } else if (!fm.equals(other.fm))
            return false;
        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;
        if (deleted != other.deleted)
            return false;
        return true;
    }

}