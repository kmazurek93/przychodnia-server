package edu.wmi.dpri.przychodnia.server.integration.test.repository;

import edu.wmi.dpri.przychodnia.server.entity.views.UsersData;
import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import edu.wmi.dpri.przychodnia.server.repository.views.UsersDataRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * Created by lupus on 11.12.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class UsersDataRepositoryIntegrationTest {

    public static final String LIKE_LOGIN_ADMIN = "%admi%";
    public static final String LIKE_EMPTY = "%%";
    public static final Sort SORT_BY_ID_ASCENDING = new Sort(ASC, "id");
    @Rule
    @Inject
    public DbScriptRule rule;

    @Inject
    private UsersDataRepository tested;


    @Test
    public void shouldDoProperSearchQuery() {
        //when
        List<UsersData> usersDatas = tested.queryOnAll(LIKE_LOGIN_ADMIN, LIKE_EMPTY, LIKE_EMPTY, LIKE_EMPTY, LIKE_EMPTY, LIKE_EMPTY);
        // then
        assertThat(usersDatas).hasSize(1);
    }

    @Test
    public void shouldDoProperSearchQueryWithPage() {
        // when
        Page<UsersData> usersDatas = tested.queryOnAllPageable(
                LIKE_EMPTY, LIKE_EMPTY, LIKE_EMPTY, LIKE_EMPTY,
                LIKE_EMPTY, LIKE_EMPTY, new PageRequest(0, 3, SORT_BY_ID_ASCENDING));
        // then
        assertThat(usersDatas.getContent()).hasSize(3);
        assertThat(usersDatas.getTotalPages()).isEqualTo(3);
    }
}
