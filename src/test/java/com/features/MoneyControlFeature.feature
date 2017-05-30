@browser
Feature: Login and select stock.
As a system user
I want to login and search for a fund 
So that I can add it to my portfolio


Background:
Given The user is a registered user.
	
	
Scenario Outline: Login to the application.
	When user tries to login with "<username>" and "<password>"
	Then user should be able to login.
	Examples: 
	|username 		|password 		|
	|demoCucumber	| democucumber	|
	
Scenario Outline: User is able to get stock quote
	When user tries to search for "<stock>"
	Then stock quote "<StockName>" should be displayed.
	
	Examples: 
	|stock|StockName   |
	|GAIL | GAIL Indi  |
	|GAIL | GAIL India |
