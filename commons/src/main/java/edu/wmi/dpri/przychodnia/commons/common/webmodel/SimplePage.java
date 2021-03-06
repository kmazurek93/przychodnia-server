package edu.wmi.dpri.przychodnia.commons.common.webmodel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jersey.repackaged.com.google.common.collect.Lists.newArrayList;



@Data
@NoArgsConstructor
public class SimplePage<T> {
    private Integer numberOfPages;
    private List<T> list = newArrayList();

}
