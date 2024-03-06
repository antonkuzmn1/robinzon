package cloud.robinzon.backend.db.fm;

import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;

import cloud.robinzon.backend.db.client.ClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fm_entity")
@EntityListeners(FmEntityListener.class)
public class FmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
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
    @JoinColumn
    private ClientEntity client;

    @Column(nullable = false)
    private boolean deleted;

    public FmEntity(
            Long id,
            String name,
            String ip,
            String title,
            String specifications,
            String description,
            int price,
            boolean vm,
            ClientEntity client,
            boolean deleted) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.title = title;
        this.specifications = specifications;
        this.description = description;
        this.price = price;
        this.vm = vm;
        this.client = client;
        this.deleted = deleted;
    }

    public FmEntity(
            String name,
            String ip,
            String title,
            String specifications,
            String description,
            int price,
            boolean vm,
            ClientEntity client) {
        this.name = name;
        this.ip = ip;
        this.title = title;
        this.specifications = specifications;
        this.description = description;
        this.price = price;
        this.vm = vm;
        this.client = client;
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
        return "FmEntity [id=" + id
                + ", timestamp=" + timestamp
                + ", name=" + name
                + ", ip=" + ip
                + ", title=" + title
                + ", specifications=" + specifications
                + ", description=" + description
                + ", price=" + price
                + ", vm=" + vm
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
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((specifications == null) ? 0 : specifications.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + price;
        result = prime * result + (vm ? 1231 : 1237);
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
        FmEntity other = (FmEntity) obj;
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
