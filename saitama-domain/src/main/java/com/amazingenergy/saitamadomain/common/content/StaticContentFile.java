package com.amazingenergy.saitamadomain.common.content;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class StaticContentFile extends ContentFile {
	private FileContentType fileContentType;
}
