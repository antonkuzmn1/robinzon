package cloud.robinzon.backend.db.client;

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
@Table(name = "client_entity")
@EntityListeners(ClientEntityListener.class)
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
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

    @Column(nullable = false)
    private int balance;

    @Column(nullable = false)
    private boolean deleted;

    public ClientEntity(
            Long id,
            String name,
            String inn,
            int discount,
            Double contractNumber,
            Date contractDate,
            String title,
            String description,
            int balance,
            boolean deleted) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.discount = discount;
        this.contractNumber = contractNumber;
        this.contractDate = contractDate;
        this.title = title;
        this.description = description;
        this.balance = balance;
        this.deleted = deleted;
    }

    public ClientEntity(
            String name,
            String inn,
            int discount,
            Double contractNumber,
            Date contractDate,
            String title,
            int balance,
            String description) {
        this.name = name;
        this.inn = inn;
        this.discount = discount;
        this.contractNumber = contractNumber;
        this.contractDate = contractDate;
        this.title = title;
        this.description = description;
        this.balance = balance;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ClientEntity [id=" + id
                + ", timestamp=" + timestamp
                + ", name=" + name
                + ", inn=" + inn
                + ", discount=" + discount
                + ", contractNumber=" + contractNumber
                + ", contractDate=" + contractDate
                + ", title=" + title
                + ", description=" + description
                + ", balance=" + balance
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
        result = prime * result + ((inn == null) ? 0 : inn.hashCode());
        result = prime * result + discount;
        result = prime * result + ((contractNumber == null) ? 0 : contractNumber.hashCode());
        result = prime * result + ((contractDate == null) ? 0 : contractDate.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + balance;
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
        ClientEntity other = (ClientEntity) obj;
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
        if (balance != other.balance)
            return false;
        if (deleted != other.deleted)
            return false;
        return true;
    }

}
