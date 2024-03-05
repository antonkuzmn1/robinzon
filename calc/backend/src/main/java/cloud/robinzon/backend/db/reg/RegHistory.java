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

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    public RegHistory(
            RegEntity regEntity,
            String brand,
            String name,
            String part,
            String serial,
            Date buyDate,
            int warrantyMonths,
            String provider,
            UserEntity changeBy) {
        this.regEntity = regEntity;
        this.brand = brand;
        this.name = name;
        this.part = part;
        this.serial = serial;
        this.buyDate = buyDate;
        this.warrantyMonths = warrantyMonths;
        this.provider = provider;
        this.changeBy = changeBy;
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
                + ", changeBy=" + changeBy
                + "]";
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
        result = prime * result + ((changeBy == null) ? 0 : changeBy.hashCode());
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
        if (changeBy == null) {
            if (other.changeBy != null)
                return false;
        } else if (!changeBy.equals(other.changeBy))
            return false;
        return true;
    }

}