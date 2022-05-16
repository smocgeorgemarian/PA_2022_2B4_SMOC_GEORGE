package persistance;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FriendId implements Serializable {
    private static final long serialVersionUID = 1623843146917995476L;
    @Column(name = "ID1", nullable = false)
    private Long id1;

    @Column(name = "ID2", nullable = false)
    private Long id2;

    public Long getId1() {
        return id1;
    }

    public void setId1(Long id1) {
        this.id1 = id1;
    }

    public Long getId2() {
        return id2;
    }

    public void setId2(Long id2) {
        this.id2 = id2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendId entity = (FriendId) o;
        return Objects.equals(this.id2, entity.id2) &&
                Objects.equals(this.id1, entity.id1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id2, id1);
    }

}