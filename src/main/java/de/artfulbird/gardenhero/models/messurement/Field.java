package de.artfulbird.gardenhero.models.messurement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.artfulbird.gardenhero.models.BaseModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "ME_FIELDS")
public class Field extends BaseModel {

    @Column
    private String name;

    @Column
    @CreationTimestamp
    private LocalDateTime implemented_at;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Sector_id", referencedColumnName = "id")
    private Sector sector;

    @OneToMany(mappedBy = "field")
    private List<Moisture> propMoisture = new ArrayList<>();

}
