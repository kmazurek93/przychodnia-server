package edu.wmi.dpri.przychodnia.server.entity;

import edu.wmi.dpri.przychodnia.server.entity.enums.ActionType;
import edu.wmi.dpri.przychodnia.server.entity.enums.EntityType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by lupus on 10.09.16.
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "action_logs")
public class ActionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "entity_id", length = 30)
    private String entityId;

    @Enumerated
    @Column(name = "entity_type")
    private EntityType entityType;

    @Enumerated
    @Column(name = "action_type")
    private ActionType actionType;

    @Column(name = "action_date")
    private DateTime actionDate;

}
