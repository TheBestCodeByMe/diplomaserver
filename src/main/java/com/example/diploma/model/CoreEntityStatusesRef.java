package com.example.diploma.model;

import com.example.diploma.enumiration.EStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "core_entity_statuses_ref")
@ToString
public class CoreEntityStatusesRef {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="core_entity_statuses_ref_id")
    private Long id;
    @Column(name = "core_entity_statuses_ref_name", nullable = false)
    private EStatus name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoreEntityStatusesRef that = (CoreEntityStatusesRef) o;
        return Objects.equals(id, that.id) && name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
