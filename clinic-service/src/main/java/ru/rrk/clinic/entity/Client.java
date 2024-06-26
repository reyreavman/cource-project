package ru.rrk.clinic.entity;

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
@Table(schema = "clinic", name = "t_client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_first_name")
    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;

    @Column(name = "c_last_name")
    @Size(max = 50)
    private String lastName;

    @Column(name = "c_phone_number")
    @NotNull
    @Size(min = 1, max = 20)
    private String phoneNumber;

    @Column(name = "c_email")
    @Size(max = 255)
    private String email;
}
