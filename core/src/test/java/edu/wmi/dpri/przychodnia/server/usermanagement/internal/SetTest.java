package edu.wmi.dpri.przychodnia.server.usermanagement.internal;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lupus on 19.11.16.
 */
public class SetTest {

    @Test
    public void testIntersection() {
        Set<Integer> setOne = newHashSet(11, 12, 13, 14, 15);
        Set<Integer> setTwo = newHashSet(10, 11, 12, 13, 14);
        Set<Integer> setThree = newHashSet(12, 13, 14, 15, 16);

        setOne.retainAll(setTwo);
        setOne.retainAll(setThree);

        assertThat(setOne).containsOnlyElementsOf(newHashSet(12, 13, 14));
    }

    @Test

    public void stringUtilsTest() {
        String out = StringUtils.join("%", null, "%");
        assertThat(out).isEqualTo("%%");
    }


}
