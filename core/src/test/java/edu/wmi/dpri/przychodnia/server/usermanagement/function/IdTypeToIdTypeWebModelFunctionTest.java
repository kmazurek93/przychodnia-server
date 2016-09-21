package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.server.entity.IdType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kmazu on 24.08.2016.
 */
public class IdTypeToIdTypeWebModelFunctionTest {

	public static final String PASSPORT = "PASSPORT";
	public static final int ID = 123;
	private IdTypeToIdTypeWebModelFunction tested = new IdTypeToIdTypeWebModelFunction();

	@Test
	public void apply() throws Exception {
		// given
		IdType idType = getSampleIdType();
		// when
		IdTypeWebModel actual = tested.apply(idType);
		// then
		assertThat(actual.getName()).isEqualTo(PASSPORT);
		assertThat(actual.getId()).isEqualTo(ID);
	}

	private IdType getSampleIdType() {
		IdType outcome = new IdType();
		outcome.setName(PASSPORT);
		outcome.setId(ID);
		return outcome;
	}

}