package com.amazingenergy.saitamadomain.common.content;

import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Data class responsible for carrying out static content data from Infispan cache to
 * service layer.
 */
@Getter
@Setter
public class OutputContentFile extends StaticContentFile implements Serializable {
    private ByteArrayOutputStream file;
}