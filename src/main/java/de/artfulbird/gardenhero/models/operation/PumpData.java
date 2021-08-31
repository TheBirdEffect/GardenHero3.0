package de.artfulbird.gardenhero.models.operation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.artfulbird.gardenhero.models.BaseModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "OP_PUMP_DATA")
public class PumpData extends BaseModel {


    @Column
    //@CreationTimestamp
    private LocalDateTime running_time_begin;

    @Column
    //@UpdateTimestamp
    private LocalDateTime running_time_end;

    @Column
    private Double average_water_consumption;

    @ManyToOne
    @JoinColumn(name = "pump_id", referencedColumnName = "id")
    private Pump pump;
}
