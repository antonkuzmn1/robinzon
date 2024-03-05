package cloud.robinzon.backend.db.net;

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
@Table(name = "net_entity")
@EntityListeners(NetEntityListener.class)
public class NetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn
    private ClientEntity client;

    @Column(nullable = false, length = 50)
    private String domain;

    @Column(nullable = false, length = 15)
    private String subnet;

    @Column(nullable = false, length = 15)
    private String mask;

    @Column(nullable = false, length = 15)
    private String dns1;

    @Column(nullable = false, length = 15)
    private String dns2;

    @Column(nullable = false, length = 15)
    private String dns3;

    @Column(nullable = false)
    private boolean cloud;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private boolean deleted;

    public NetEntity(
            Long id,
            ClientEntity client,
            String domain,
            String subnet,
            String mask,
            String dns1,
            String dns2,
            String dns3,
            boolean cloud,
            String title,
            String description,
            boolean deleted) {
        this.id = id;
        this.client = client;
        this.domain = domain;
        this.subnet = subnet;
        this.mask = mask;
        this.dns1 = dns1;
        this.dns2 = dns2;
        this.dns3 = dns3;
        this.cloud = cloud;
        this.title = title;
        this.description = description;
    }

    public NetEntity(
            ClientEntity client,
            String domain,
            String subnet,
            String mask,
            String dns1,
            String dns2,
            String dns3,
            boolean cloud,
            String title,
            String description) {
        this.client = client;
        this.domain = domain;
        this.subnet = subnet;
        this.mask = mask;
        this.dns1 = dns1;
        this.dns2 = dns2;
        this.dns3 = dns3;
        this.cloud = cloud;
        this.title = title;
        this.description = description;
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

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getDns1() {
        return dns1;
    }

    public void setDns1(String dns1) {
        this.dns1 = dns1;
    }

    public String getDns2() {
        return dns2;
    }

    public void setDns2(String dns2) {
        this.dns2 = dns2;
    }

    public String getDns3() {
        return dns3;
    }

    public void setDns3(String dns3) {
        this.dns3 = dns3;
    }

    public boolean isCloud() {
        return cloud;
    }

    public void setCloud(boolean cloud) {
        this.cloud = cloud;
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

    @Override
    public String toString() {
        return "NetEntity [id=" + id
                + ", timestamp=" + timestamp
                + ", client=" + client
                + ", domain=" + domain
                + ", subnet=" + subnet
                + ", mask=" + mask
                + ", dns1=" + dns1
                + ", dns2=" + dns2
                + ", dns3=" + dns3
                + ", cloud=" + cloud
                + ", title=" + title
                + ", description=" + description
                + ", deleted=" + deleted
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        result = prime * result + ((domain == null) ? 0 : domain.hashCode());
        result = prime * result + ((subnet == null) ? 0 : subnet.hashCode());
        result = prime * result + ((mask == null) ? 0 : mask.hashCode());
        result = prime * result + ((dns1 == null) ? 0 : dns1.hashCode());
        result = prime * result + ((dns2 == null) ? 0 : dns2.hashCode());
        result = prime * result + ((dns3 == null) ? 0 : dns3.hashCode());
        result = prime * result + (cloud ? 1231 : 1237);
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
        NetEntity other = (NetEntity) obj;
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
        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;
        if (domain == null) {
            if (other.domain != null)
                return false;
        } else if (!domain.equals(other.domain))
            return false;
        if (subnet == null) {
            if (other.subnet != null)
                return false;
        } else if (!subnet.equals(other.subnet))
            return false;
        if (mask == null) {
            if (other.mask != null)
                return false;
        } else if (!mask.equals(other.mask))
            return false;
        if (dns1 == null) {
            if (other.dns1 != null)
                return false;
        } else if (!dns1.equals(other.dns1))
            return false;
        if (dns2 == null) {
            if (other.dns2 != null)
                return false;
        } else if (!dns2.equals(other.dns2))
            return false;
        if (dns3 == null) {
            if (other.dns3 != null)
                return false;
        } else if (!dns3.equals(other.dns3))
            return false;
        if (cloud != other.cloud)
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