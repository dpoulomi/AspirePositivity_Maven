package com.ap.mango.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mongodb.BasicDBObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@Entity
@Table(name = "Users")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users extends BasicDBObject{

	public static final String COLLECTION_NAME = "Users";
	@Id
	@Column(name = "_id")
	private String userId;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "emailId")
	private String emailId;

	@Column(name = "password")
	private String password;

	@Column(name = "username")
	private String username;


}
