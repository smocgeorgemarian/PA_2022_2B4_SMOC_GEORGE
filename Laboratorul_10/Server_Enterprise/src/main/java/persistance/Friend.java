package persistance;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FRIENDS")
public class Friend implements AbstractEntity{
    @EmbeddedId
    private FriendId id;

    public FriendId getId() {
        return id;
    }

    public void setId(FriendId id) {
        this.id = id;
    }

//TODO [JPA Buddy] generate columns from DB
}