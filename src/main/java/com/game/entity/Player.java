package com.game.entity;

import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder  // Генерирует построитель
@NoArgsConstructor  // Пустой конструктор
@AllArgsConstructor // Конструктор со всеми параметрами
@Entity
@NamedQueries({
        @NamedQuery(
                name = "Player_getAllCount",
                query = "SELECT COUNT(p) FROM Player p"
        ),
//        @NamedQuery(
//                name = "Admin",
//                query = "from Employee e where e.smth = 'admin'"
//        ),
//        // Другие NamedQuery могут быть добавлены здесь
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Player_getAll",
                query = "SELECT * FROM rpg.player",
                resultClass = Player.class
        ),
        // Другие NamedQuery могут быть добавлены здесь
})
@Table(name = "player", schema = "rpg")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 12, nullable = false)
    private String name;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Race race;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Profession profession;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "banned", nullable = false)
    private Boolean banned;

    @Column(name = "level", nullable = false)
    private Integer level;
}