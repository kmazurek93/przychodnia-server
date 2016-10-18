package edu.wmi.dpri.przychodnia.server.entity;

import edu.wmi.dpri.przychodnia.server.entity.enums.ActionType;
import edu.wmi.dpri.przychodnia.server.entity.enums.EntityType;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by lupus on 10.09.16.
 */
@Entity
@Table(name = "action_log")
public class ActionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "entity_id")
    private String entityId;

    @Enumerated
    @Column(name = "entity_type")
    private EntityType entityType;

    @Enumerated
    @Column(name = "action_type")
    private ActionType actionType;

    @Column(name = "action_date")
    private DateTime actionDate;

    public DateTime getActionDate() {
        return actionDate;
    }

    public void setActionDate(DateTime actionDate) {
        this.actionDate = actionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}
