package cloud.robinzon.backend.db.fm;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import cloud.robinzon.backend.security.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fm_history")
@IdClass(FmHistoryKey.class)
public class FmHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private FmEntity fmEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 15)
    private String ip;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String specifications;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private boolean vm;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    /**
     * <h3>New entity</h3>
     *
     * @param fmEntity
     * @param name
     * @param ip
     * @param title
     * @param specifications
     * @param description
     * @param price
     * @param vm
     * @param changeBy
     * @param deleted
     */
    public FmHistory(
            FmEntity fmEntity,
            String name,
            String ip,
            String title,
            String specifications,
            String description,
            int price,
            boolean vm,
            UserEntity changeBy,
            boolean deleted) {
        this.fmEntity = fmEntity;
        this.name = name;
        this.ip = ip;
        this.title = title;
        this.specifications = specifications;
        this.description = description;
        this.price = price;
        this.vm = vm;
        this.changeBy = changeBy;
        this.deleted = deleted;
    }

    /**
     * <h3>Delete entity</h3>
     *
     * @param fmEntity
     * @param changeBy
     */
    public FmHistory(
            FmEntity fmEntity,
            UserEntity changeBy) {
        this.fmEntity = fmEntity;
        this.name = fmEntity.getName();
        this.ip = fmEntity.getIp();
        this.title = fmEntity.getTitle();
        this.specifications = fmEntity.getSpecifications();
        this.description = fmEntity.getDescription();
        this.price = fmEntity.getPrice();
        this.vm = fmEntity.getVm();
        this.changeBy = changeBy;
        this.deleted = true;
    }

    public FmEntity getFmEntity() {
        return fmEntity;
    }

    public void setFmEntity(FmEntity fmEntity) {
        this.fmEntity = fmEntity;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getVm() {
        return vm;
    }

    public void setVm(boolean vm) {
        this.vm = vm;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(UserEntity changeBy) {
        this.changeBy = changeBy;
    }

    @Override
    public String toString() {
        return "FmHistory [fmEntity=" + fmEntity
                + ", timestamp=" + timestamp
                + ", name=" + name
                + ", ip=" + ip
                + ", title=" + title
                + ", specifications=" + specifications
                + ", description=" + description
                + ", price=" + price
                + ", vm=" + vm
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
        result = prime * result + ((fmEntity == null) ? 0 : fmEntity.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((specifications == null) ? 0 : specifications.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + price;
        result = prime * result + (vm ? 1231 : 1237);
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
        FmHistory other = (FmHistory) obj;
        if (fmEntity == null) {
            if (other.fmEntity != null)
                return false;
        } else if (!fmEntity.equals(other.fmEntity))
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
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (specifications == null) {
            if (other.specifications != null)
                return false;
        } else if (!specifications.equals(other.specifications))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (price != other.price)
            return false;
        if (vm != other.vm)
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