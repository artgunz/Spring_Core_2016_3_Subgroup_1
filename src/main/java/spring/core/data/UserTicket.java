package spring.core.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "user_ticket")
@DiscriminatorValue("Y")
public class UserTicket extends Ticket {

    @OneToOne
    User user;

    @Embedded
    Price price;

    public UserTicket() {
    }

    public UserTicket(final ShowEvent showEvent, final Seat seat, final User user, final Price price) {
        super(showEvent, seat);
        this.user = user;
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public Price getPrice() {
        return price;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof UserTicket)) return false;

        final UserTicket that = (UserTicket) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(user, that.user)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(user)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user", user)
                .append("price", price)
                .toString();
    }
}
