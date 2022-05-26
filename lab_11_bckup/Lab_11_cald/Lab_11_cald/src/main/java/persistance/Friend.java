package persistance;

import javax.persistence.*;

@Entity
@Table(name = "FRIENDS")
@NamedQueries({
        @NamedQuery(name = "Friend.findById",
                query = "select e from Friend e where e.id.id1 = :id or e.id.id2 = :id"),
        @NamedQuery(name = "Friend.findAll",
                query = "select e from Friend e")

})
public class Friend implements AbstractEntity{
    @EmbeddedId
    private FriendId id;

    public Friend() {
    }

    public Friend(FriendId friendId) {
        this.id = friendId;
    }

    public FriendId getId() {
        return id;
    }

    public void setId(FriendId id) {
        this.id = id;
    }
}