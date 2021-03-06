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
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "OP_PUMPS")
public class Pump extends BaseModel {

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isRunning;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "op_center_id", referencedColumnName = "id")
    private OperationalCenter operationalCenter;

    @OneToMany(mappedBy = "pump", cascade = CascadeType.PERSIST)
    private List<PumpData> opPumpData = new ArrayList<>();

}
