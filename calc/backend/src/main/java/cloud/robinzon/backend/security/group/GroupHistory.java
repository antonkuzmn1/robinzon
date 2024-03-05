package cloud.robinzon.backend.security.group;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cloud.robinzon.backend.security.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "security_group_history")
@IdClass(GroupHistoryKey.class)
public class GroupHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private GroupEntity groupEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String description;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ManyToMany
    @JsonIgnoreProperties("groups")
    private Set<UserEntity> users = new HashSet();

    @ManyToOne
    @JoinColumn
    private UserEntity changeBy;

    public GroupHistory(
            GroupEntity groupEntity,
            String name,
            String title,
            String description,
            Set<UserEntity> users,
            UserEntity changeBy) {
        this.groupEntity = groupEntity;
        this.name = name;
        this.title = title;
        this.description = description;
        this.users = users;
        this.changeBy = changeBy;
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
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

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(UserEntity changeBy) {
        this.changeBy = changeBy;
    }

    @Override
    public String toString() {
        return "GroupHistory [groupEntity=" + groupEntity
                + ", timestamp=" + timestamp
                + ", name=" + name
                + ", title=" + title
                + ", description=" + description
                + ", users=" + users
                + ", changeBy=" + changeBy
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((groupEntity == null) ? 0 : groupEntity.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((users == null) ? 0 : users.hashCode());
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
        GroupHistory other = (GroupHistory) obj;
        if (groupEntity == null) {
            if (other.groupEntity != null)
                return false;
        } else if (!groupEntity.equals(other.groupEntity))
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
        if (users == null) {
            if (other.users != null)
                return false;
        } else if (!users.equals(other.users))
            return false;
        if (changeBy == null) {
            if (other.changeBy != null)
                return false;
        } else if (!changeBy.equals(other.changeBy))
            return false;
        return true;
    }

}
