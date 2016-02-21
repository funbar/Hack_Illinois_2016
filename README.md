# Hack_Illinois_2016
Google Maps is the real mvp
- A location based application that helps hopelessly lost, wandering souls navigate with the most ease through unfamiliar territory.

# Key Features

- Notifications based off a customizable widget. (Optional)
- Organizers overriding a user's map to show specific points.
- Able to locate utilities for handicap people as well.
- Handles Multi-level buildings
- User Privileges
- Allow users to enter a destination and tag utilities within their vicinity.
- Incorporate a Flic for user interaction.
- Crowd-sourced
- Useful for events at hackathons such as scavenger hunt.
- Markers may contain useful notes (ie. Only accessible by ACM members)
- (SmartWatch) Suggest: gives alerts to user of utilities nearby.
- (SmartWatch) Demand: can be used to mark spot on map

# Things to think about regarding alerts:
- Launch automatically: Users should not need to reach out to launch apps on a Wear device. A Wear device is not a phone. The Wear app should be aware of the context--time, location and activity--and should insert the relevant card accordingly.
- Be “glanceable”: Since users are wearing the devices on their wrists, it should be as easy to read the notification as it is to see the time on a traditional watch. Keeping that in mind, pay special attention to the title/text: Keep it to a minimum--easy to read at a glance and relevant.
- Effectively leverage Suggest and Demand: According to the Android Wear developer page, “Android Wear is like a great personal assistant … it only interrupts you when absolutely necessary, and it’s always on hand to provide a ready answer.” To offer this experience, developers should be thoughtful about when and how they use the Suggest and Demand models.
- Require zero to low interaction with the user: It’s important to develop applications with the typically small size of wearable devices in mind. Applications have to be easy for users to interact with, which means using voice or simple swipe/tap gestures.
