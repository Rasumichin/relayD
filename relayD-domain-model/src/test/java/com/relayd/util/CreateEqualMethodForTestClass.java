package com.relayd.util;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   30.08.2016
 * status   initial
 */
public class CreateEqualMethodForTestClass {

	public String createTestEqualsWithMyself() {
		return "@Test public void testEqualsWithMyself() {boolean result = sut.equals(sut);assertTrue(result);}";
	}

	public String createTestEqualsWithNull() {
		return "@Test public void testEqualsWithNull() {boolean result = sut.equals(null);assertFalse(result);}";
	}

	public String createTestEqualsWithNotCompatibleClass() {
		return "@Test public void testEqualsWithNotCompatibleClass() {boolean result = sut.equals(new String());assertFalse(result);}";
	}

	public String createTestEqualsWithParameterValueIsNull(String classname, String valueName, String type) {
		StringBuilder builder = new StringBuilder("@Test public void testEqualsWith");
		builder.append(valueName);
		builder.append("ValueIsNull() {sut.set");
		builder.append(valueName);
		builder.append("(null);");
		builder.append(classname);
		builder.append(" secondSut = new ");
		builder.append(classname);
		builder.append("();secondSut.set");
		builder.append(valueName);
		builder.append("((");
		builder.append(type);
		builder.append(") 1);boolean result = sut.equals(secondSut);assertFalse(result);}");

		return builder.toString();
	}

	public String createTestEqualsWithBothMasterCompanyNumberValuesAreNull(String classname, String valueName) {
		StringBuilder builder = new StringBuilder("@Test public void testEqualsWithBoth");
		builder.append(valueName);
		builder.append("ValuesAreNull() {sut.set");
		builder.append(valueName);
		builder.append("(null);");
		builder.append(classname);
		builder.append(" secondSut = new ");
		builder.append(classname);
		builder.append("();secondSut.set");
		builder.append(valueName);
		builder.append("(null);boolean result = sut.equals(secondSut);assertTrue(result);}");

		return builder.toString();
	}

	public String createTestEqualsWithParameterTwoDiffrentValues(String classname, String valueName, String type) {
		StringBuilder builder = new StringBuilder("@Test public void testEqualsWith");
		builder.append(valueName);
		builder.append("TwoDiffrentValues() {sut.set");
		builder.append(valueName);
		builder.append("((");
		builder.append(type);
		builder.append(") 1);");
		builder.append(classname);
		builder.append(" secondSut = new ");
		builder.append(classname);
		builder.append("();secondSut.set");
		builder.append(valueName);
		builder.append("((");
		builder.append(type);
		builder.append(") 2);boolean result = sut.equals(secondSut);assertFalse(result);}");
		return builder.toString();
	}

	public String createTestEqualsWithParameterSameValues(String classname, String valueName, String type) {
		String firstLetterOfValueNameLowerCase = valueName.toLowerCase().substring(0, 1);
		String restOfValueName = valueName.substring(1);

		StringBuilder builder = new StringBuilder("@Test public void testEqualsWith");
		builder.append(valueName);
		builder.append("SameValues() {");
		builder.append(type);
		builder.append(" " + firstLetterOfValueNameLowerCase);
		builder.append(restOfValueName);
		builder.append(" = (");
		builder.append(type);
		builder.append(") 1;sut.set");
		builder.append(valueName);
		builder.append("(");
		builder.append(firstLetterOfValueNameLowerCase + restOfValueName);
		builder.append(");");
		builder.append(classname);
		builder.append(" secondSut = new ");
		builder.append(classname);
		builder.append("();secondSut.set");
		builder.append(valueName);
		builder.append("(");
		builder.append(firstLetterOfValueNameLowerCase + restOfValueName);
		builder.append(");");
		builder.append("boolean result = sut.equals(secondSut);assertTrue(result);}");

		return builder.toString();
	}

}
