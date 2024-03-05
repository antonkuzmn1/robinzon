package cloud.robinzon.backend.security.user;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cloud.robinzon.backend.security.group.GroupEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "security_user_history")
@IdClass(UserHistoryKey.class)
public class UserHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 50)
    private String fullname;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String description;

    @ManyToMany
    @JsonIgnoreProperties("users")
    @JoinTable(name = "security_m2m_user_group_history")
    private Set<GroupEntity> groups = new HashSet<>();

    @ManyToOne
    @JoinColumn
    private UserEntity changeBy;

    public UserHistory(
            UserEntity userEntity,
            String username,
            String password,
            String fullname,
            String title,
            String description,
            Set<GroupEntity> groups,
            UserEntity changeBy) {
        this.userEntity = userEntity;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.title = title;
        this.description = description;
        this.groups = groups;
        this.changeBy = changeBy;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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

    public Set<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupEntity> groups) {
        this.groups = groups;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(UserEntity changeBy) {
        this.changeBy = changeBy;
    }

    @Override
    public String toString() {
        return "UserHistory [userEntity=" + userEntity
                + ", timestamp=" + timestamp
                + ", username=" + username
                + ", password=" + password
                + ", fullname=" + fullname
                + ", title=" + title
                + ", description=" + description
                + ", groups=" + groups
                + ", changeBy=" + changeBy
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userEntity == null) ? 0 : userEntity.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((groups == null) ? 0 : groups.hashCode());
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
        UserHistory other = (UserHistory) obj;
        if (userEntity == null) {
            if (other.userEntity != null)
                return false;
        } else if (!userEntity.equals(other.userEntity))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
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
        if (groups == null) {
            if (other.groups != null)
                return false;
        } else if (!groups.equals(other.groups))
            return false;
        if (changeBy == null) {
            if (other.changeBy != null)
                return false;
        } else if (!changeBy.equals(other.changeBy))
            return false;
        return true;
    }

}
