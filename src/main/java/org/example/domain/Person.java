package org.example.domain;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "person")
@XmlRootElement(name = "person")
public class Person
{
  @Generated(GenerationTime.INSERT)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  @SuppressWarnings("unused")
  private Long id;

  @Column(name = "email_address", unique = true)
  @NotNull
  @Size(max = 254)
  private String emailAddress;

  @Column(name = "first_name")
  @NotNull
  @Size(max = 50)
  private String firstName;

  @Column(name = "last_name")
  @NotNull
  @Size(max = 50)
  private String lastName;

  public Long getID()
  {
    return id;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setEmailAddress(final String emailAddress)
  {
    this.emailAddress = emailAddress;
  }

  public void setFirstName(final String firstName)
  {
    this.firstName = firstName;
  }

  public void setLastName(final String lastName)
  {
    this.lastName = lastName;
  }
}
