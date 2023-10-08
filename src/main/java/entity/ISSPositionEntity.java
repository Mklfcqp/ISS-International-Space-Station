package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "position")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ISSPositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "timestamp")
    private long timestamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "recorded_at")
    private Date recordedAt;

}
