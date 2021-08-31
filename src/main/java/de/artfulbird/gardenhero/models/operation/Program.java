package de.artfulbird.gardenhero.models.operation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.artfulbird.gardenhero.models.BaseModel;
import de.artfulbird.gardenhero.models.measurement.Field;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "OP_PROGRAMS")
public class Program extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column
    private String type;

    @Column(nullable = false)
    private float moistureThresholdLow;

    @Column(nullable = false)
    private float moistureThresholdHigh;

    @Column(nullable = false)
    private float wateringDurationPerIntervalInSeconds;

    @Column(nullable = false)
    private float wateringBreakPerIntervalInSeconds;

    @Column(nullable = false)
    private int wateringFrequencyPerIntervalInSeconds;

    @Column
    @ColumnDefault(value = "false")
    private boolean isEnabled;

    @ManyToOne
    @JoinColumn(name = "op_center_id", referencedColumnName = "id")
    private OperationalCenter operationalCenter;

}
