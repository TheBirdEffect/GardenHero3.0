package de.artfulbird.gardenhero.models.messurement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.artfulbird.gardenhero.models.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Field_id", referencedColumnName = "id")
    private Field field;

}
