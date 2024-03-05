package cloud.robinzon.backend.security.user;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserHistoryKey implements Serializable {

    private UserEntity userEntity;
    private Timestamp timestamp;

    public UserHistoryKey(
            UserEntity userEntity,
            Timestamp timestamp) {
        this.userEntity = userEntity;
        this.timestamp = timestamp;
    }

    public UserHistoryKey() {
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

    @Override
    public String toString() {
        return "UserHistoryKey [userEntity=" + userEntity
                + ", timestamp=" + timestamp + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userEntity == null) ? 0 : userEntity.hashCode());
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
        UserHistoryKey other = (UserHistoryKey) obj;
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
        return true;
    }

}