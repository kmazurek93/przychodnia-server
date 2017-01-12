package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.PersonalDataWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.builder.PersonalDataWebModelBuilder.aPersonalDataWebModel;


@Component
public class PersonToPersonalDataWebModelFunction implements Function<Person, PersonalDataWebModel> {
    @Override
    public PersonalDataWebModel apply(Person person) {
        return aPersonalDataWebModel().withPesel(person.getPESEL()).withBirthDate(person.getBirthDate().getMillis())
                .withTelephone(person.getTelephone()).withBirthPlace(person.getBirthPlace())
                .withFirstName(person.getFirstName()).withMiddleName(person.getMiddleName())
                .withIdNumber(person.getIdNumber()).withIdTypeNo(person.getIdType().getId())
                .withSexId(person.getSex().getId()).withLastName(person.getLastName())
                .build();
    }
}
