package de.artfulbird.gardenhero.models.measurement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.artfulbird.gardenhero.models.BaseModel;
import de.artfulbird.gardenhero.models.operation.Program;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
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

    @ManyToOne
    @JoinColumn(name = "Sector_id", referencedColumnName = "id")
    private Sector sector;

    @OneToMany(mappedBy = "field", cascade = CascadeType.PERSIST)
    private List<Moisture> propMoisture = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "programm_id", referencedColumnName = "id")
    private Program program;

}
