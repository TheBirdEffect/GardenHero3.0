package de.artfulbird.gardenhero.models.operation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.artfulbird.gardenhero.models.BaseModel;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Per_pump")
public class Pump extends BaseModel {

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isRunning;

    @Column
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name =  "op_center_id", referencedColumnName = "id")
    private OperationalCenter operationalCenter;

    @OneToMany(mappedBy = "per_pump")
    private List<pump_runtime> pump_runtimes = new ArrayList<>();

    @Builder
    public Pump (boolean isRunning, String name) {
        this.isRunning = isRunning;
        this.name = name;
    }
}
