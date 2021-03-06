package edu.wmi.dpri.przychodnia.server.entity;

import edu.wmi.dpri.przychodnia.server.entity.enums.NewsType;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;


@Data
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content", columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, columnDefinition = "NVARCHAR(30)", length = 30)
    private NewsType type;

    @Column(name = "news_date")
    private DateTime newsDate;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "anonymous", nullable = false)
    private Boolean anonymous;

}
