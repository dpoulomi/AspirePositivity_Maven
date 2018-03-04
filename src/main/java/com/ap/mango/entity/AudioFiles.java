package com.ap.mango.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.InputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AudioFiles")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AudioFiles implements Serializable {


    @Id
    @Column(name = "_id")
    private int id;

    /*@Column(name = "fileId")
    private int fileId;
*/

    @Column(name = "username")
    private String username;

    @Column(name = "file")
    private InputStream file;
}
