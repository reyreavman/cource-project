package ru.rrk.clinic.entity.vet;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "clinic", name = "t_vet")
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_first_name")
    @NotNull
    @Size(min = 1, max = 100)
    private String firstName;

    @Column(name = "c_last_name")
    @NotNull
    @Size(min = 1, max = 100)
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "c_speciality_id")
    private Speciality speciality;
}
