package cloud.robinzon.backend.db.reg;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reg_entity")
@EntityListeners(RegEntityListener.class)
public class RegEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
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

    @Column(nullable = false)
    private boolean deleted;

    public RegEntity(
            Long id,
            String brand,
            String name,
            String part,
            String serial,
            Date buyDate,
            int warrantyMonths,
            String provider,
            String title,
            String description,
            boolean deleted) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.part = part;
        this.serial = serial;
        this.buyDate = buyDate;
        this.warrantyMonths = warrantyMonths;
        this.provider = provider;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    public RegEntity(
            String brand,
            String name,
            String part,
            String serial,
            Date buyDate,
            int warrantyMonths,
            String title,
            String description,
            String provider) {
        this.brand = brand;
        this.name = name;
        this.part = part;
        this.serial = serial;
        this.buyDate = buyDate;
        this.warrantyMonths = warrantyMonths;
        this.title = title;
        this.description = description;
        this.provider = provider;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "RegEntity [id=" + id
                + ", timestamp=" + timestamp
                + ", brand=" + brand
                + ", name=" + name
                + ", part=" + part
                + ", serial=" + serial
                + ", buyDate=" + buyDate
                + ", warrantyMonths=" + warrantyMonths
                + ", provider=" + provider
                + ", title=" + title
                + ", description=" + description
                + ", deleted=" + deleted
                + "]";
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        RegEntity other = (RegEntity) obj;
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
        if (deleted != other.deleted)
            return false;
        return true;
    }

}