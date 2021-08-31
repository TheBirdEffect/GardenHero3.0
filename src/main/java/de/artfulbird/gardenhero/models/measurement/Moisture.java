package de.artfulbird.gardenhero.models.measurement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.artfulbird.gardenhero.models.BaseModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "ME_MOISTURE")
public class Moisture extends BaseModel {

    @Column
    private float value;

    @Column
    @CreationTimestamp
    private LocalDateTime measurement_time;

    @ManyToOne
    @JoinColumn(name = "Field_id", referencedColumnName = "id")
    private Field field;

}
