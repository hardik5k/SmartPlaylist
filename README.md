# SmartPlaylist

## Problem Statement

It has always been incredibly difficult to mutually agree on a playlist at group events or dance parties where everyone’s taste of music 
greatly diverges.

## Proposed Solution
* We designed an app called ‘SmartPlaylist’ which lets you create interactive playlists, based on a system of voting. 
* Simple swipes to upvote and downvote can push your favourite songs up the playlist which helps build a common interest among voters.
* You can add your favourite songs to the playlist with their YouTube URLs, through which the song details are extracted and displayed for all users to see.
* Create and manage your list of events with your friends on a community calendar, where the various events can be scheduled by you and your friends with unique playlists for the events.

 <img src="https://github.com/hardik5k/SmartPlaylist/blob/main/App%20Screenshots/Screenshot%202022-01-09%20at%2012.32.18.png" width="500" />
 <img src="https://github.com/hardik5k/SmartPlaylist/blob/main/App%20Screenshots/Screenshot%202022-01-09%20at%2012.49.40.png" width="500" />
  



## Functionality & Concepts used
The app has a simple, yet an interactive and modern interface. A few android concepts used to build this app are:
* Recycler view used to create an interactive scrolling and swiping experience
* Retrofit2 for handling HTTP Requests to Youtube
* ViewModels with Firebase RealTime Database for syncing playlist and events between devices
* Shared Preferences for storing global attributes locally
* LiveData to observe changes in events or any playlist. 

Besides, we used Adaptors, Fragments, ContraintLayout, CardViews, Navigation and other core Android functionalities.

## Instructions of Use
* As the app opens, the calendar can be used to scroll to an appropriate day and add events.
* Inside each event, the '+' button is used to add songs using their YouTube URL.
* Each card of the event list can be right swiped to upvote a song and left swiped to undo the upvote if you want to. Tapping on the card provides useful song information.

## Application Link & Future Scope

APP REPO LINK : https://github.com/hardik5k/SmartPlaylist

Once the basic functionality is fully tested, we plan to use this app for conducting events in our college.
* We will also add a feature of merging playlists of different groups for the same event, making the sorting algorithm smarter to choose equally between songs of different regions and groups participating in the same event.
* Exporting the playlist to any music player application and controlling the player from within our application.

Sample Working Video: https://drive.google.com/drive/folders/1nW0zdOGh88Zzuy5jke7u1f_E19O2Tb6l?usp=sharing


