package com.amazingenergy.saitamadomain.catalog.product.domain.manufacturer;

import com.amazingenergy.saitamadomain.common.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class ManufacturerDescription extends Description {
    private String url;
    private Integer urlClicked;
    private Date dateLastClick;
}
