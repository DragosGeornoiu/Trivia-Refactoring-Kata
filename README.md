# Trivia-Refactoring-Kata

The trivia kata is usually designed to be a Legacy Code Refactoring Kata, which means it has no unit tests provided for it.
It is used in many “Legacy Code Retreat” events, which are a whole day, as described by Emily Bache in her book "The Coding Dojo Handbook".

A version of that kata can be found on Emily Bache's github: https://github.com/emilybache/trivia/tree/master/java

The Trivia Kata version used here is from Victor Rentea: https://github.com/victorrentea/kata-trivia-java

The advantage of this version is that it provides a reliable test which transforms the execise into a refactoring kata instead of a legacy kata. 


##Topics to be discussed while implementing
* Can we identify duplicated code and extract it into method?
* What is a pure function? Can we create methods with no state and no side effect? 
* Can we extract responsibilities or logic from the Game class into separate classes?
* Did the tests fail at any point of the refactoring? Why?


##### The code refactoring of this kata can be viewed on branch  'technical-meeting'
##### The code changes were done in two sessions of one and a half hours each.
