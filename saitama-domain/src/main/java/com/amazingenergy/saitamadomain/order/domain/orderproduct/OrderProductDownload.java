package com.amazingenergy.saitamadomain.order.domain.orderproduct;

import com.amazingenergy.core.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderProductDownload extends Entity<UUID, OrderProductDownload> {
    public final static int DEFAULT_DOWNLOAD_MAX_DAYS = 31;

    private String filename;
    private int maxDays = DEFAULT_DOWNLOAD_MAX_DAYS;
    private int downloadCount;

    public OrderProductDownload() {
        super(UUID.randomUUID());
    }
}
