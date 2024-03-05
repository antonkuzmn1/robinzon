package cloud.robinzon.backend.settings.vm.price;

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
@Table(name = "settings_vm_price_history")
@IdClass(VmPriceHistoryKey.class)
public class VmPriceHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private VmPriceEntity vmPriceEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private int cost;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    public VmPriceHistory(
            VmPriceEntity vmPriceEntity,
            String name,
            int cost,
            UserEntity changeBy) {
        this.vmPriceEntity = vmPriceEntity;
        this.cost = cost;
        this.changeBy = changeBy;
    }

    public VmPriceEntity getVmPriceEntity() {
        return vmPriceEntity;
    }

    public void setVmPriceEntity(VmPriceEntity vmPriceEntity) {
        this.vmPriceEntity = vmPriceEntity;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(UserEntity changeBy) {
        this.changeBy = changeBy;
    }

    @Override
    public String toString() {
        return "VmPriceHistory [vmPriceEntity=" + vmPriceEntity
                + ", timestamp=" + timestamp
                + ", name=" + name
                + ", cost=" + cost
                + ", changeBy=" + changeBy
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vmPriceEntity == null) ? 0 : vmPriceEntity.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + cost;
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
        VmPriceHistory other = (VmPriceHistory) obj;
        if (vmPriceEntity == null) {
            if (other.vmPriceEntity != null)
                return false;
        } else if (!vmPriceEntity.equals(other.vmPriceEntity))
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
        if (cost != other.cost)
            return false;
        if (changeBy == null) {
            if (other.changeBy != null)
                return false;
        } else if (!changeBy.equals(other.changeBy))
            return false;
        return true;
    }

}
