package cloud.robinzon.backend.db.reg;

import java.sql.Timestamp;
import java.util.Date;

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
@Table(name = "reg_history")
@IdClass(RegHistoryKey.class)
public class RegHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private RegEntity regEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 100)
    private String brand;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String part;

    @Column(nullable = false, length = 50)
    private String serial;

    @Column(nullable = false)
    private Date buyDate;

    @Column(nullable = false)
    private int warrantyMonths;

    @Column(nullable = false, length = 50)
    private String provider;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    /**
     * <h3>New entity</h3>
     *
     * @param regEntity
     * @param brand
     * @param name
     * @param part
     * @param serial
     * @param buyDate
     * @param warrantyMonths
     * @param provider
     * @param title
     * @param description
     * @param changeBy
     * @param deleted
     */
    public RegHistory(
            RegEntity regEntity,
            String brand,
            String name,
            String part,
            String serial,
            Date buyDate,
            int warrantyMonths,
            String provider,
            String title,
            String description,
            UserEntity changeBy,
            boolean deleted) {
        this.regEntity = regEntity;
        this.brand = brand;
        this.name = name;
        this.part = part;
        this.serial = serial;
        this.buyDate = buyDate;
        this.warrantyMonths = warrantyMonths;
        this.provider = provider;
        this.title = title;
        this.description = description;
        this.changeBy = changeBy;
        this.deleted = deleted;
    }

    /**
     * <h3>Delete entity</h3>
     *
     * @param regEntity
     * @param changeBy
     */
    public RegHistory(
            RegEntity regEntity,
            UserEntity changeBy) {
        this.regEntity = regEntity;
        this.changeBy = changeBy;
        this.brand = regEntity.getBrand();
        this.name = regEntity.getName();
        this.part = regEntity.getPart();
        this.serial = regEntity.getSerial();
        this.buyDate = regEntity.getBuyDate();
        this.warrantyMonths = regEntity.getWarrantyMonths();
        this.provider = regEntity.getProvider();
        this.title = regEntity.getTitle();
        this.description = regEntity.getDescription();
        this.deleted = true;
    }

    public RegEntity getRegEntity() {
        return regEntity;
    }

    public void setRegEntity(RegEntity regEntity) {
        this.regEntity = regEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(UserEntity changeBy) {
        this.changeBy = changeBy;
    }

    @Override
    public String toString() {
        return "RegHistory [regEntity=" + regEntity
                + ", timestamp=" + timestamp
                + ", brand=" + brand
                + ", name=" + name
                + ", part=" + part
                + ", serial=" + serial
                + ", buyDate=" + buyDate
                + ", warrantyMonths=" + warrantyMonths
                + ", provider=" + provider
                + ", title=" + title
                + ", description" + description
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((regEntity == null) ? 0 : regEntity.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((brand == null) ? 0 : brand.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((part == null) ? 0 : part.hashCode());
        result = prime * result + ((serial == null) ? 0 : serial.hashCode());
        result = prime * result + ((buyDate == null) ? 0 : buyDate.hashCode());
        result = prime * result + warrantyMonths;
        result = prime * result + ((provider == null) ? 0 : provider.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        RegHistory other = (RegHistory) obj;
        if (regEntity == null) {
            if (other.regEntity != null)
                return false;
        } else if (!regEntity.equals(other.regEntity))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (brand == null) {
            if (other.brand != null)
                return false;
        } else if (!brand.equals(other.brand))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (part == null) {
            if (other.part != null)
                return false;
        } else if (!part.equals(other.part))
            return false;
        if (serial == null) {
            if (other.serial != null)
                return false;
        } else if (!serial.equals(other.serial))
            return false;
        if (buyDate == null) {
            if (other.buyDate != null)
                return false;
        } else if (!buyDate.equals(other.buyDate))
            return false;
        if (warrantyMonths != other.warrantyMonths)
            return false;
        if (provider == null) {
            if (other.provider != null)
                return false;
        } else if (!provider.equals(other.provider))
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