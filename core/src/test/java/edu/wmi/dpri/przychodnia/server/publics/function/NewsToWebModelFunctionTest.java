package edu.wmi.dpri.przychodnia.server.publics.function;

import edu.wmi.dpri.przychodnia.commons.common.webmodel.NewsWebModel;
import edu.wmi.dpri.przychodnia.server.entity.News;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.entity.enums.NewsType;
import org.assertj.core.data.Offset;
import org.joda.time.DateTime;
import org.junit.Test;

import static edu.wmi.dpri.przychodnia.server.publics.function.NewsToWebModelFunction.ZESPOL_PRZYCHODNI;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by lupus on 05.01.17.
 */
public class NewsToWebModelFunctionTest {

    public static final String TITLE = "Title";
    public static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
            "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " +
            "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
            "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";
    public static final String FIRST = "First";
    public static final String LAST = "Last";
    public static final long ID = 12434L;
    public static final Offset<Long> OFFSET = Offset.offset(5000L);
    public static final String NEWS = "NEWS";
    private NewsToWebModelFunction tested = new NewsToWebModelFunction();


    @Test
    public void shouldConvertProperly() throws Exception {
        //given
        News news = getSampleNews();
        //when
        NewsWebModel actual = tested.apply(news);
        //then
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(ID);
        assertThat(actual.getTimestamp()).isCloseTo(new DateTime().getMillis(), OFFSET);
        assertThat(actual.getContent()).isEqualTo(LOREM_IPSUM);
        assertThat(actual.getAuthor()).isEqualTo(FIRST + " " + LAST);
        assertThat(actual.getType()).isEqualTo(NEWS);
        assertThat(actual.getTitle()).isEqualTo(TITLE);
    }

    @Test
    public void shouldReturnProperAuthorWhenAnonymous() throws Exception {
        //given
        News news = getSampleNews();
        news.setAnonymous(true);
        //when
        NewsWebModel actual = tested.apply(news);
        //then
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(ID);
        assertThat(actual.getTimestamp()).isCloseTo(new DateTime().getMillis(), OFFSET);
        assertThat(actual.getContent()).isEqualTo(LOREM_IPSUM);
        assertThat(actual.getAuthor()).isEqualTo(ZESPOL_PRZYCHODNI);
        assertThat(actual.getType()).isEqualTo(NEWS);
        assertThat(actual.getTitle()).isEqualTo(TITLE);
    }

    private News getSampleNews() {
        News news = new News();
        news.setId(ID);
        news.setNewsDate(new DateTime());
        news.setTitle(TITLE);
        news.setContent(LOREM_IPSUM);
        news.setAnonymous(false);
        Person person = new Person();
        person.setFirstName(FIRST);
        person.setLastName(LAST);
        User user = new User();
        user.setPerson(person);
        news.setAuthor(user);
        news.setType(NewsType.NEWS);
        return news;
    }

}