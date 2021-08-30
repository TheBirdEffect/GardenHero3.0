package de.artfulbird.gardenhero.models.operation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.artfulbird.gardenhero.models.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "OP_PROGRAMS")
public class Program extends BaseModel {

    @Column(nullable = false)
    private String name;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "op_center_id", referencedColumnName = "id")
    private OperationalCenter operationalCenter;


}
