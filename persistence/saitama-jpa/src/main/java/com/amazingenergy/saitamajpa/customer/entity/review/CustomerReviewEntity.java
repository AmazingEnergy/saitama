package com.amazingenergy.saitamajpa.customer.entity.review;

import com.amazingenergy.corejpa.audit.AuditListener;
import com.amazingenergy.corejpa.audit.Auditable;
import com.amazingenergy.corejpa.audit.EmbeddableAuditSection;
import com.amazingenergy.saitamajpa.customer.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "CustomerReview",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CustomerId", "ReviewedCustomerId"})})
public class CustomerReviewEntity implements Auditable {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private EmbeddableAuditSection auditSection;

    @Column(name = "Rating")
    private Double reviewRating;

    @Column(name = "ReviewRead")
    private Long reviewRead;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ReviewDate")
    private Date reviewDate;

    @Column(name = "Status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private CustomerEntity customer;

    @OneToOne
    @JoinColumn(name = "ReviewedCustomerId")
    private CustomerEntity reviewedCustomer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customerReview")
    private Set<CustomerReviewDescriptionEntity> descriptions = new HashSet<>();
}
