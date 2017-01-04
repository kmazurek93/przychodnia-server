package edu.wmi.dpri.przychodnia.commons.common.webmodel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jersey.repackaged.com.google.common.collect.Lists.newArrayList;


/**
 * Created by lupus on 04.01.17.
 */
@Data
@NoArgsConstructor
public class SimplePage<T> {
    private Integer numberOfPages;
    private List<T> list = newArrayList();

}
