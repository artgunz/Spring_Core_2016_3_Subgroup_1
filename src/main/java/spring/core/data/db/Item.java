package spring.core.data.db;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pk", nullable = false)
    Long pk;

    public Long getPk() {
        return pk;
    }

    public void setPk(final Long pk) {
        this.pk = pk;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pk", pk)
                .toString();
    }
}
