package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.SexWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Sex;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.builder.SexWebModelBuilder.aSexWebModel;


@Component
public class SexToWebModelFunction implements Function<Sex, SexWebModel> {


    @Override
    public SexWebModel apply(Sex input) {
        return aSexWebModel().withId(input.getId()).withName(input.getName()).build();
    }

    public List<SexWebModel> applyToList(List<Sex> input) {
        return input.stream().map(this::apply).collect(Collectors.toList());
    }
}
