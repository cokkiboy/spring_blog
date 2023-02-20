package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Board {

	 @Id
	  @GeneratedValue(strategy =GenerationType.IDENTITY )
	 private int id;
	 
	 @Column(nullable=false,length=100)
	 private String title;
	 
	 @Lob
	 private String content;
	 
	 @ColumnDefault("0")
	 private int count;
	 
	 @ManyToOne(fetch=FetchType.EAGER)// Many =board ,User =One
	 @JoinColumn(name="userid")
	 private User user;//db는 오브젝트를 저장할수없다. Fk자바는 오브젝트를 저장할수있다
	 
	 @OneToMany(mappedBy="board")
	
	 private List<Reply> reply;
	 @CreationTimestamp
	 private Timestamp createDate;
}
