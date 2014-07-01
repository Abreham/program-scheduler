About
======
Eclipse Java project

Import the project as a Java Project, run tests as a java-application.

Domain
======
The program has domain objects: Talk,Session and Track.

Input
=====
It reads the input from a file sample.txt, using a regular expression.
convert the input into a List of String objects(title,talk_duration).


Services
=======
It has Services for each domain element, and custom-exception InvalidScheduleException 

Tests
=========
TestReader a file reader Test.
TestTalkService tests the TalkService logic
TestTrackService test both SessionService and TrackService
