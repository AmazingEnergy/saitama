package com.amazingenergy.saitamadomain.common.content;

import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.io.Serializable;

@Getter
@Setter
public class InputContentFile extends StaticContentFile implements Serializable {
    private InputStream file;
    private String path;
}