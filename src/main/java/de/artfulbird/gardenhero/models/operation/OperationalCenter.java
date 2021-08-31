package de.artfulbird.gardenhero.models.operation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.artfulbird.gardenhero.models.BaseModel;
import de.artfulbird.gardenhero.models.measurement.Sector;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "OP_CENTERS")
public class OperationalCenter extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column
    private String ipAddress;

    @OneToMany(mappedBy = "operationalCenter", cascade = CascadeType.MERGE)
    private List<Pump> pumps = new ArrayList<>();

    @OneToMany(mappedBy = "operationalCenter")
    private List<Sector> sectors = new ArrayList<>();

    @OneToMany(mappedBy = "operationalCenter")
    private List<Program> programs = new ArrayList<>();


}
