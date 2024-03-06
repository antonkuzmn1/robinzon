package cloud.robinzon.backend.db.vpn.user;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import cloud.robinzon.backend.db.vpn.server.VpnServerEntity;
import cloud.robinzon.backend.security.user.UserEntity;
import cloud.robinzon.backend.settings.vpn.type.VpnTypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vpn_user_history")
@IdClass(VpnUserHistoryKey.class)
public class VpnUserHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private VpnUserEntity vpnUserEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(nullable = false)
    private VpnServerEntity vpnServerEntity;

    @ManyToOne
    @JoinColumn
    private VpnTypeEntity vpnTypeEntity;

    @Column(nullable = false, length = 15)
    private String ip;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 100)
    private String fullname;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String description;

    @ManyToOne
    @JoinColumn
    private UserEntity changeBy;

    public VpnUserHistory(
            VpnUserEntity vpnUserEntity,
            VpnServerEntity vpnServerEntity,
            VpnTypeEntity vpnTypeEntity,
            String ip,
            String username,
            String password,
            String fullname,
            String title,
            String description,
            UserEntity changeBy) {
        this.vpnUserEntity = vpnUserEntity;
        this.vpnServerEntity = vpnServerEntity;
        this.vpnTypeEntity = vpnTypeEntity;
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.title = title;
        this.description = description;
        this.changeBy = changeBy;
    }

    public VpnUserEntity getVpnUserEntity() {
        return vpnUserEntity;
    }

    public void setVpnUserEntity(VpnUserEntity vpnUserEntity) {
        this.vpnUserEntity = vpnUserEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public VpnServerEntity getVpnServerEntity() {
        return vpnServerEntity;
    }

    public void setVpnServerEntity(VpnServerEntity vpnServerEntity) {
        this.vpnServerEntity = vpnServerEntity;
    }

    public VpnTypeEntity getVpnTypeEntity() {
        return vpnTypeEntity;
    }

    public void setVpnTypeEntity(VpnTypeEntity vpnTypeEntity) {
        this.vpnTypeEntity = vpnTypeEntity;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
        return "VpnUserHistory [vpnUserEntity=" + vpnUserEntity
                + ", timestamp=" + timestamp
                + ", vpnServerEntity=" + vpnServerEntity
                + ", vpnTypeEntity=" + vpnTypeEntity
                + ", ip=" + ip
                + ", username=" + username
                + ", password=" + password
                + ", fullname=" + fullname
                + ", title=" + title
                + ", description=" + description
                + ", changeBy=" + changeBy
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vpnUserEntity == null) ? 0 : vpnUserEntity.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((vpnServerEntity == null) ? 0 : vpnServerEntity.hashCode());
        result = prime * result + ((vpnTypeEntity == null) ? 0 : vpnTypeEntity.hashCode());
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        VpnUserHistory other = (VpnUserHistory) obj;
        if (vpnUserEntity == null) {
            if (other.vpnUserEntity != null)
                return false;
        } else if (!vpnUserEntity.equals(other.vpnUserEntity))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (vpnServerEntity == null) {
            if (other.vpnServerEntity != null)
                return false;
        } else if (!vpnServerEntity.equals(other.vpnServerEntity))
            return false;
        if (vpnTypeEntity == null) {
            if (other.vpnTypeEntity != null)
                return false;
        } else if (!vpnTypeEntity.equals(other.vpnTypeEntity))
            return false;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (fullname == null) {
            if (other.fullname != null)
                return false;
        } else if (!fullname.equals(other.fullname))
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
        return true;
    }

}
