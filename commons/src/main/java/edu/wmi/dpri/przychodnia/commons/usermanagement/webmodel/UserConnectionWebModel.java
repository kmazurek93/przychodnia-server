package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

/**
 * Created by kmazu on 06.07.2016.
 */
public class UserConnectionWebModel {
    private Integer id;

    private Integer parentId;
    private Integer childId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }
}
