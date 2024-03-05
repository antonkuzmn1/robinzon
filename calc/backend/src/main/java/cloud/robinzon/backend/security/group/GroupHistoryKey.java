package cloud.robinzon.backend.security.group;

import java.io.Serializable;
import java.sql.Timestamp;

public class GroupHistoryKey implements Serializable {

    private GroupEntity groupEntity;
    private Timestamp timestamp;

    public GroupHistoryKey(
            GroupEntity groupEntity,
            Timestamp timestamp) {
        this.groupEntity = groupEntity;
        this.timestamp = timestamp;
    }

    public GroupHistoryKey() {
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

    @Override
    public String toString() {
        return "GroupHistoryKey [groupEntity=" + groupEntity
                + ", timestamp=" + timestamp
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((groupEntity == null) ? 0 : groupEntity.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
        GroupHistoryKey other = (GroupHistoryKey) obj;
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
        return true;
    }

}