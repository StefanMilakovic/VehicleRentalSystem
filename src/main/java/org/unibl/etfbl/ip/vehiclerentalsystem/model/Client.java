package org.unibl.etfbl.ip.vehiclerentalsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "user_id")
public class Client extends User
{
    /*
    @Lob
    @Column(name = "nationality", nullable = false)
    private String nationality;
     */

    @Enumerated(EnumType.STRING)
    @Column(name = "nationality", nullable = false)
    private Nationality nationality;

    @Column(name = "id_document_number", nullable = false, length = 45)
    private String idDocumentNumber;

    @Column(name = "driving_license_number", nullable = false, length = 45)
    private String drivingLicenseNumber;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @Column(name = "avatar_url")
    private String avatarUrl;


    public enum Nationality {
        domestic, foreign
    }
}

