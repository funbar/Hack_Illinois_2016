<br>
<h1><p align="center"><a href="http://devpost.com/software/manimap" target="_blank">Minimap</a></p></h1>

<p align="center">
  <a href="http://hackillinois2016s.devpost.com/"><b>Hack Illinois 2016</b></a>
</p>

<p align="center">
<br>
<b><a href="#inspiration"> Inspiration </a></b>|
<b><a href="#what-it-does"> What it does </a></b>|
<b><a href="#how-its-built"> How its built </a></b>

</p>
<br>

---




##Inspiration

We arrived at the Hackathon, got lost, and had no idea where to put our stuff or where to pee. We were easily inspired to create an app that assisted us finding all these locations without having to ask anyone.
What it does

## What it does
Manimap is a real-time, crowd-based contribution map that allow the user to find key locations as bathrooms, food spots, sponsors, and staff-tables around the Hackathon. It can technically be used for absolutely any big event since its easy to set up and very easy to distribute to the guests so everyone can know where everything is.

## How its built
We used a combination of technologies such as Google Maps API, Android, AngularJS, and Firebase.

- android
- angular.js
- firebase
- google-maps
- android-studio
- nosql

## Challenges we ran into

We first attempted to do real-time GPS locations inside the building. However, we found these to be somewhat unreliable since the user's location would vary within a one-mile radius. Instead, we just made a crowd-based contributed map. We also had some trouble doing the Angular routing for the web-app version of Manimap.
Accomplishments that we're proud of

Everyone was able to contribute a piece of work. There were many technologies that we never used like Firebase, NoSQL, android wear SDK, and the google maps API, that we were able to work with after a tough time and succeed with.
What we learned

The world is in need of indoor positioning systems for very accurate location in buildings or certain areas. Beacons are currently the best solutions for this problem but they can be very expensive and not very friendly to set up.

## What's next

We will expand Manimap to be used for all big events, add on more user friendly features, and think of a way to improve location accuracy for better usage.



Try it out:   http://manimap.me





## Hack_Illinois_2016
Google Maps is the real mvp
- A location based application that helps hopelessly lost, wandering souls navigate with the most ease through unfamiliar territory.

## Key Features

- Notifications based off a customizable widget. (Optional)
- Organizers overriding a user's map to show specific points.
- Able to locate utilities for handicap people as well.
- Handles Multi-level buildings
- User Privileges
- Allow users to enter a destination and tag utilities within their vicinity.
- Crowd-sourced
- Useful for events at hackathons such as scavenger hunt.
- Markers may contain useful notes (ie. Only accessible by ACM members)
- (SmartWatch) Suggest: gives alerts to user of utilities nearby.
- (SmartWatch) Demand: can be used to mark spot on map

## Things to think about regarding alerts:
- Launch automatically: Users should not need to reach out to launch apps on a Wear device. A Wear device is not a phone. The Wear app should be aware of the context--time, location and activity--and should insert the relevant card accordingly.
- Be “glanceable”: Since users are wearing the devices on their wrists, it should be as easy to read the notification as it is to see the time on a traditional watch. Keeping that in mind, pay special attention to the title/text: Keep it to a minimum--easy to read at a glance and relevant.
- Effectively leverage Suggest and Demand: According to the Android Wear developer page, “Android Wear is like a great personal assistant … it only interrupts you when absolutely necessary, and it’s always on hand to provide a ready answer.” To offer this experience, developers should be thoughtful about when and how they use the Suggest and Demand models.
- Require zero to low interaction with the user: It’s important to develop applications with the typically small size of wearable devices in mind. Applications have to be easy for users to interact with, which means using voice or simple swipe/tap gestures.


## NoSQL Database



![Firebase GUI](https://raw.githubusercontent.com/funbar/Hack_Illinois_2016/master/firebase.PNG)

![Firebase GUI](https://raw.githubusercontent.com/funbar/Hack_Illinois_2016/master/firebase2.PNG)

![Firebase GUI](https://raw.githubusercontent.com/funbar/Hack_Illinois_2016/master/firebase3.PNG)
