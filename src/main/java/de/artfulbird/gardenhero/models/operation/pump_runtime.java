package de.artfulbird.gardenhero.models.operation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.artfulbird.gardenhero.models.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "OP_PUMP_DATA")
public class pump_runtime extends BaseModel {

    @Builder
    public pump_runtime() {}

    @Column
    @CreationTimestamp
    private LocalDateTime running_time_begin;

    @Column
    @UpdateTimestamp
    private LocalDateTime running_time_end;

    @Column
    private Double average_water_consumption;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pump_id", referencedColumnName = "id")
    private Pump pump;
}
