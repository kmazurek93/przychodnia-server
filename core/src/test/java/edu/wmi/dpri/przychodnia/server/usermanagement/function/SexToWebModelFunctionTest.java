package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.SexWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Sex;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lupus on 08.01.17.
 */
public class SexToWebModelFunctionTest {

    public static final String M = "M";
    public static final long ID = 123L;
    public static final String K = "K";
    public static final long ID1 = 33L;
    private SexToWebModelFunction tested = new SexToWebModelFunction();


    @Test
    public void apply() throws Exception {
        //given
        Sex sex = getSampleSex(ID, M);
        // when
        SexWebModel actual = tested.apply(sex);
        // then
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(ID);
        assertThat(actual.getName()).isEqualTo(M);
    }

    private Sex getSampleSex(Long id, String name) {
        Sex sex = new Sex();
        sex.setId(id);
        sex.setName(name);
        return sex;
    }

    @Test
    public void applyToList() throws Exception {
        //given
        List<Sex> sexes = newArrayList(getSampleSex(ID1, K), getSampleSex(ID, M));
        // when
        List<SexWebModel> actual = tested.applyToList(sexes);
        // then
        assertThat(actual).hasSize(2);
        assertThat(actual.get(0).getId()).isEqualTo(ID1);
        assertThat(actual.get(0).getName()).isEqualTo(K);
        assertThat(actual.get(1).getId()).isEqualTo(ID);
        assertThat(actual.get(1).getName()).isEqualTo(M);
    }

}