package test.resliv.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.List;


@Entity
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    @JsonManagedReference
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "city")
    private List<DescriptionsEntity> descriptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CityEntity() {
    }

    public List<DescriptionsEntity> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<DescriptionsEntity> descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return city;
    }
}
