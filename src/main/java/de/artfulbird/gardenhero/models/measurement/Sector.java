package de.artfulbird.gardenhero.models.measurement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.artfulbird.gardenhero.models.BaseModel;
import de.artfulbird.gardenhero.models.operation.OperationalCenter;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "ME_SECTORS")
public class Sector extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column
    @CreationTimestamp
    private LocalDateTime implementedAt;

    @Column
    private String ipAddress;

    @ManyToOne
    @JoinColumn(name = "op_center_id", referencedColumnName = "id")
    private OperationalCenter operationalCenter;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.PERSIST)
    private List<Field> fields = new ArrayList<>();

}
