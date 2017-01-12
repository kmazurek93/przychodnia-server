package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataSimpleModel;
import edu.wmi.dpri.przychodnia.server.entity.Address;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.User;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.builder.UserDataSimpleModelBuilder.anUserDataSimpleModel;
import static org.apache.commons.lang3.StringUtils.join;


@Component
public class UserToUserDataSimpleModelFunction implements Function<User, UserDataSimpleModel> {

    @Override
    public UserDataSimpleModel apply(User user) {
        String address = getConcatenatedAddress(user.getPerson().getAddress());
        String mailingAddress = null;
        if (user.getPerson().getMailingAddress() != null) {
            mailingAddress = getConcatenatedAddress(user.getPerson().getMailingAddress());
        }
        Long userId = user.getId();
        String pesel = user.getPerson().getPESEL();
        String mail = user.getEmailAddress();
        String phone = user.getPerson().getTelephone();
        String name = getName(user.getPerson());
        return anUserDataSimpleModel().withAddress(address).withMailingAddress(mailingAddress).withEmail(mail).withPesel(pesel)
                .withName(name).withTelephone(phone).withUserId(userId).build();
    }

    private String getName(Person person) {
        return join(person.getFirstName(), " ",
                (person.getMiddleName() != null ? join(person.getMiddleName(), " ") : ""), person.getLastName());
    }

    private String getConcatenatedAddress(Address addr) {
        String houseAndApt = join(addr.getHouse(), (addr.getApartment() != null ? join("/", addr.getApartment(), ", ") : ", "));
        return join(addr.getStreet(), " ", houseAndApt, addr.getPostCode(), " ", addr.getCity());
    }

    public List<UserDataSimpleModel> applyToList(List<User> users) {
        return users.stream().map(this::apply).collect(Collectors.toList());
    }
}
