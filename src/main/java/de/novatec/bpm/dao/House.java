package de.novatec.bpm.dao;

public class House {

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public int getPersons() {
    return persons;
  }

  public void setPersons(int persons) {
    this.persons = persons;
  }

  private String street;
  private int persons;

}
