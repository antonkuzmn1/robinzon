package cloud.robinzon.backend.db.client;

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
@Table(name = "client_history")
@IdClass(ClientHistoryKey.class)
public class ClientHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private ClientEntity clientEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 12)
    private String inn;

    @Column(nullable = false)
    private int discount;

    @Column
    private Double contractNumber;

    @Column
    private Date contractDate;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    public ClientHistory(
            ClientEntity clientEntity,
            String name,
            String inn,
            int discount,
            Double contractNumber,
            Date contractDate,
            String title,
            String description,
            UserEntity changeBy,
            boolean deleted) {
        this.clientEntity = clientEntity;
        this.name = name;
        this.inn = inn;
        this.discount = discount;
        this.contractNumber = contractNumber;
        this.contractDate = contractDate;
        this.title = title;
        this.description = description;
        this.changeBy = changeBy;
        this.deleted = deleted;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
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

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Double getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(Double contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
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

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(UserEntity changeBy) {
        this.changeBy = changeBy;
    }

    @Override
    public String toString() {
        return "ClientHistory [clientEntity=" + clientEntity
                + ", timestamp=" + timestamp
                + ", name=" + name
                + ", inn=" + inn
                + ", discount=" + discount
                + ", contractNumber=" + contractNumber
                + ", contractDate=" + contractDate
                + ", title=" + title
                + ", description=" + description
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
        result = prime * result + ((clientEntity == null) ? 0 : clientEntity.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((inn == null) ? 0 : inn.hashCode());
        result = prime * result + discount;
        result = prime * result + ((contractNumber == null) ? 0 : contractNumber.hashCode());
        result = prime * result + ((contractDate == null) ? 0 : contractDate.hashCode());
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
        ClientHistory other = (ClientHistory) obj;
        if (clientEntity == null) {
            if (other.clientEntity != null)
                return false;
        } else if (!clientEntity.equals(other.clientEntity))
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
        if (inn == null) {
            if (other.inn != null)
                return false;
        } else if (!inn.equals(other.inn))
            return false;
        if (discount != other.discount)
            return false;
        if (contractNumber == null) {
            if (other.contractNumber != null)
                return false;
        } else if (!contractNumber.equals(other.contractNumber))
            return false;
        if (contractDate == null) {
            if (other.contractDate != null)
                return false;
        } else if (!contractDate.equals(other.contractDate))
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
