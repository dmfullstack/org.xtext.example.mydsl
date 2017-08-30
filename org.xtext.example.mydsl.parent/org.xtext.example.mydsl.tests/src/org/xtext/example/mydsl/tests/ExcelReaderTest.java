package org.xtext.example.mydsl.tests;

import java.util.Map;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

@SuppressWarnings("all")
public class ExcelReaderTest {
  @Rule
  public TestName name = new TestName();
  
  @Test
  public void readRowByValue() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<<<<<< Execute test: ");
    String _methodName = this.name.getMethodName();
    _builder.append(_methodName);
    _builder.append(" >>>>>>>");
    System.out.println(_builder);
    String excelFileName = "Scenarios.xls";
    Map<String, String> row = ExcelReader.readRow(excelFileName, "Scenario1", "c");
    Assert.assertNotNull(row);
    Assert.assertFalse(row.isEmpty());
    Set<Map.Entry<String, String>> _entrySet = row.entrySet();
    for (final Map.Entry<String, String> entry : _entrySet) {
      StringConcatenation _builder_1 = new StringConcatenation();
      String _key = entry.getKey();
      _builder_1.append(_key);
      _builder_1.append(" => ");
      String _value = entry.getValue();
      _builder_1.append(_value);
      InputOutput.<String>println(_builder_1.toString());
    }
    InputOutput.<String>println("--------------------------------------------------------\n");
  }
  
  @Test
  public void readRowByValueNotFound() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<<<<<< Execute test: ");
    String _methodName = this.name.getMethodName();
    _builder.append(_methodName);
    _builder.append(" >>>>>>>");
    InputOutput.<String>println(_builder.toString());
    String excelFileName = "Scenarios.xls";
    Map<String, String> row = ExcelReader.readRow(excelFileName, "Scenario", "c");
    Assert.assertNotNull(row);
    Assert.assertTrue(row.isEmpty());
    InputOutput.<String>println("--------------------------------------------------------\n");
  }
  
  @Test
  public void readRowByHeaderColumn() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<<<<<< Execute test: ");
    String _methodName = this.name.getMethodName();
    _builder.append(_methodName);
    _builder.append(" >>>>>>>");
    InputOutput.<String>println(_builder.toString());
    String excelFileName = "Scenarios.xls";
    Map<String, String> row = ExcelReader.readRow(excelFileName, "Scenario1", "Mother\'s Name", "E");
    Assert.assertNotNull(row);
    Assert.assertFalse(row.isEmpty());
    Set<Map.Entry<String, String>> _entrySet = row.entrySet();
    for (final Map.Entry<String, String> entry : _entrySet) {
      StringConcatenation _builder_1 = new StringConcatenation();
      String _key = entry.getKey();
      _builder_1.append(_key);
      _builder_1.append(" => ");
      String _value = entry.getValue();
      _builder_1.append(_value);
      InputOutput.<String>println(_builder_1.toString());
    }
    InputOutput.<String>println("--------------------------------------------------------\n");
  }
  
  @Test
  public void readRowByHeaderColumnNotFound() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<<<<<< Execute test: ");
    String _methodName = this.name.getMethodName();
    _builder.append(_methodName);
    _builder.append(" >>>>>>>");
    InputOutput.<String>println(_builder.toString());
    String excelFileName = "Scenarios.xls";
    Map<String, String> row = ExcelReader.readRow(excelFileName, "Scenario1", "Mother\'s Name", "O");
    Assert.assertNotNull(row);
    Assert.assertTrue(row.isEmpty());
    InputOutput.<String>println("--------------------------------------------------------\n");
  }
  
  @Test
  public void readRowScenario2() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<<<<<< Execute test: ");
    String _methodName = this.name.getMethodName();
    _builder.append(_methodName);
    _builder.append(" >>>>>>>");
    InputOutput.<String>println(_builder.toString());
    String excelFileName = "Scenarios2.xls";
    Map<String, String> row = ExcelReader.readRow(excelFileName, "Scenario1", "Id", "1");
    Assert.assertNotNull(row);
    Assert.assertFalse(row.isEmpty());
    Set<Map.Entry<String, String>> _entrySet = row.entrySet();
    for (final Map.Entry<String, String> entry : _entrySet) {
      StringConcatenation _builder_1 = new StringConcatenation();
      String _key = entry.getKey();
      _builder_1.append(_key);
      _builder_1.append(" -> ");
      String _value = entry.getValue();
      _builder_1.append(_value);
      System.out.println(_builder_1);
    }
    InputOutput.<String>println("--------------------------------------------------------\n");
  }
}
