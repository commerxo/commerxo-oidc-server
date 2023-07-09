package com.commerxo.commerxoblogservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

@Entity
@Table(
        name = "tag",
        uniqueConstraints = @UniqueConstraint(
                name = "tag_unique",
                columnNames = {"tag_name"}
        )
)
public class Tag extends Auditable<String>{

    private String id;
    private String tagName;
    private String description;
    private Set<Post> posts;

    @Id
    @UuidGenerator
    @Column(name = "tag_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "tag_name")
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "tag")
    @JsonIgnore
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
