#
# This is a comment!
#

package com.company.appl.MyPackage

Feature: Employee API # free text

	As a ... 
	I want to ... 
	In order to ... 
	This is just free text
	
	Background: # swagger info.title
		Given "Service Employee" # swagger info.title
		And basePath /api

	Scenario Outline: asa
		Given aa <col1> <col2> <col3> <col4>
		Examples: 
			| col1 | col2 | col3 | col4 |
			| v1   | 1.1  | "a"  | 1    |
			| v2   | 1.2  | 'b'  | 2    |
			| v3   | 1.3  | "c"  | 3    |
			| v3   | 1.4  | 'd'  | 4    | 
	
	Scenario: Test 
		Given some state
		And foo bar 
		When 1 == 1
		Then do somtething
