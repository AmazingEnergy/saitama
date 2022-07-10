package com.amazingenergy.saitamadomain.common.content;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ContentFile {
    private String fileName;
    private String mimeType;
}
