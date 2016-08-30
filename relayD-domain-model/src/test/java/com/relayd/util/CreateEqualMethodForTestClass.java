package com.relayd.util;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   30.08.2016
 * status   initial
 */
public class CreateEqualMethodForTestClass {
	private String carrigeReturnLineFeed = "\r\n";

	public String createTestEqualsWithMyself() {
		return "@Test" + carrigeReturnLineFeed + "public void testEqualsWithMyself() {" + carrigeReturnLineFeed + carrigeReturnLineFeed + "boolean result = sut.equals(sut);" + carrigeReturnLineFeed + carrigeReturnLineFeed + "assertTrue(result);}";
	}

	public String createTestEqualsWithNull() {
		return "@Test" + carrigeReturnLineFeed + "public void testEqualsWithNull() {" + carrigeReturnLineFeed + carrigeReturnLineFeed + "boolean result = sut.equals(null);" + carrigeReturnLineFeed + carrigeReturnLineFeed + "assertFalse(result);}";
	}

	public String createTestEqualsWithNotCompatibleClass() {
		return "@Test" + carrigeReturnLineFeed + "public void testEqualsWithNotCompatibleClass() {" + carrigeReturnLineFeed + carrigeReturnLineFeed + "boolean result = sut.equals(new String());" + carrigeReturnLineFeed + carrigeReturnLineFeed + "assertFalse(result);}";
	}

	public String createTestEqualsWithParameterValueIsNull(String classname, String valueName, String type) {
		StringBuilder builder = new StringBuilder("@Test" + carrigeReturnLineFeed + "public void testEqualsWith");
		builder.append(valueName);
		builder.append("ValueIsNull() {" + carrigeReturnLineFeed + "sut.set");
		builder.append(valueName);
		builder.append("(null);" + carrigeReturnLineFeed + carrigeReturnLineFeed);
		builder.append(classname);
		builder.append(" secondSut = new ");
		builder.append(classname);
		builder.append("();" + carrigeReturnLineFeed + carrigeReturnLineFeed + "secondSut.set");
		builder.append(valueName);
		builder.append("((");
		builder.append(type);
		builder.append(") 1);" + carrigeReturnLineFeed + carrigeReturnLineFeed + "boolean result = sut.equals(secondSut);" + carrigeReturnLineFeed + carrigeReturnLineFeed + "assertFalse(result);}");

		return builder.toString();
	}

	public String createTestEqualsWithBothParameterValuesAreNull(String classname, String valueName) {
		StringBuilder builder = new StringBuilder("@Test" + carrigeReturnLineFeed + "public void testEqualsWithBoth");
		builder.append(valueName);
		builder.append("ValuesAreNull() {" + carrigeReturnLineFeed + "sut.set");
		builder.append(valueName);
		builder.append("(null);" + carrigeReturnLineFeed + carrigeReturnLineFeed);
		builder.append(classname);
		builder.append(" secondSut = new ");
		builder.append(classname);
		builder.append("();" + carrigeReturnLineFeed + carrigeReturnLineFeed + "secondSut.set");
		builder.append(valueName);
		builder.append("(null);" + carrigeReturnLineFeed + carrigeReturnLineFeed + "boolean result = sut.equals(secondSut);" + carrigeReturnLineFeed + carrigeReturnLineFeed + "assertTrue(result);}");

		return builder.toString();
	}

	public String createTestEqualsWithParameterTwoDiffrentValues(String classname, String valueName, String type) {
		StringBuilder builder = new StringBuilder("@Test" + carrigeReturnLineFeed + "public void testEqualsWith");
		builder.append(valueName);
		builder.append("TwoDiffrentValues() {" + carrigeReturnLineFeed + "sut.set");
		builder.append(valueName);
		builder.append("((");
		builder.append(type);
		builder.append(") 1);" + carrigeReturnLineFeed + carrigeReturnLineFeed);
		builder.append(classname);
		builder.append(" secondSut = new ");
		builder.append(classname);
		builder.append("();" + carrigeReturnLineFeed + carrigeReturnLineFeed + "secondSut.set");
		builder.append(valueName);
		builder.append("((");
		builder.append(type);
		builder.append(") 2);" + carrigeReturnLineFeed + carrigeReturnLineFeed + "boolean result = sut.equals(secondSut);" + carrigeReturnLineFeed + carrigeReturnLineFeed + "assertFalse(result);}");
		return builder.toString();
	}

	public String createTestEqualsWithParameterSameValues(String classname, String valueName, String type) {
		String firstLetterOfValueNameLowerCase = valueName.toLowerCase().substring(0, 1);
		String restOfValueName = valueName.substring(1);

		StringBuilder builder = new StringBuilder("@Test" + carrigeReturnLineFeed + "public void testEqualsWith");
		builder.append(valueName);
		builder.append("SameValues() {" + carrigeReturnLineFeed);
		builder.append(type);
		builder.append(" " + firstLetterOfValueNameLowerCase);
		builder.append(restOfValueName + "Value");
		builder.append(" = (");
		builder.append(type);
		builder.append(") 1;" + carrigeReturnLineFeed + carrigeReturnLineFeed + "sut.set");
		builder.append(valueName);
		builder.append("(");
		builder.append(firstLetterOfValueNameLowerCase + restOfValueName + "Value");
		builder.append(");" + carrigeReturnLineFeed + carrigeReturnLineFeed);
		builder.append(classname);
		builder.append(" secondSut = new ");
		builder.append(classname);
		builder.append("();" + carrigeReturnLineFeed + carrigeReturnLineFeed + "secondSut.set");
		builder.append(valueName);
		builder.append("(");
		builder.append(firstLetterOfValueNameLowerCase + restOfValueName + "Value");
		builder.append(");" + carrigeReturnLineFeed + carrigeReturnLineFeed);
		builder.append("boolean result = sut.equals(secondSut);" + carrigeReturnLineFeed + carrigeReturnLineFeed + "assertTrue(result);}");

		return builder.toString();
	}

}
