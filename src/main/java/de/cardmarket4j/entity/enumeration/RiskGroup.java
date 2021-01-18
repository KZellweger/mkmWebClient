package de.cardmarket4j.entity.enumeration;

public enum RiskGroup {
  NO_RISK(0, "no risk"), LOW_RISK(1, "low risk"), HIGH_RISK(2, "high risk");

  private final String displayValue;
  private final int id;

  RiskGroup(int id, String displayValue) {
    this.displayValue = displayValue;
    this.id = id;
  }

  public static RiskGroup parseId(int id) {
    for (RiskGroup e : RiskGroup.values()) {
      if (e.getId() == id) {
        return e;
      }
    }
    throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
  }

  public static RiskGroup parseValue(String value) {
    for (RiskGroup e : RiskGroup.values()) {
      if (e.getDisplayValue().equals(value)) {
        return e;
      }
    }
    throw new IllegalArgumentException("Couldn't find an enum matching this value: " + value);
  }

  @Override
  public String toString() {
    return displayValue;
  }

  private String getDisplayValue() {
    return displayValue;
  }

  private int getId() {
    return id;
  }
}
