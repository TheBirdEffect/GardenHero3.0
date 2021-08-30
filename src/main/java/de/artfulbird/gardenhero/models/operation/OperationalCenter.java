package de.artfulbird.gardenhero.models.operation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.artfulbird.gardenhero.models.BaseModel;
import de.artfulbird.gardenhero.models.messurement.Sector;
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
@Table(name = "Op_center")
public class OperationalCenter extends BaseModel {

    @Column
    private String name;

    @Column
    private String ipAddress;

    @OneToMany(mappedBy = "op_center")
    private List<Pump> pumps = new ArrayList<>();

    @OneToMany(mappedBy = "op_center")
    private List<Sector> sectors = new ArrayList<>();

    @Builder
    public OperationalCenter(String name, String ipAddress) {
        this.name = name;
        this.ipAddress = ipAddress;
    }

}
