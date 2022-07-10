package com.amazingenergy.saitamajpa.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CredentialsReset {
    @Column(name ="ResetCredentialsRequest", length=256)
    private String credentialsRequest;

    @Temporal(TemporalType.DATE)
    @Column(name = "ResetCredentialsRequestExpiry")
    private Date credentialsRequestExpiry = new Date();
}
