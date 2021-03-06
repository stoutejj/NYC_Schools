# NYC Schools

### Screenshots
<image src="screenshots/img_1.png" width="250"> <image src="screenshots/img_2.png" width="250"> 

<image src="screenshots/img_3.png" width="250"> <image src="screenshots/img_4.png" width="250"> 

### Features

- SearchBar
  - make it easier for users to find a school by name
- Call Button
  - opens the dialer for users to call the school
- School Info Button
  - redirects the user to further school information  
- In the School Info View:
    - School's name
    - School's SAT Scores
    - School's Overview
    - School's Contact Information
      - Address
        - when clicked on redirects user to google maps
      - Phone 
        - when clicked on redirects user to the dialer
      - Email
        - when clicked on redirects the user to send an email
      - Website
        - when click on prompts the user to make a choice on how to open 
          - Options:
            1. WebView
            2. WebBrowser

### How to Use the app 
When the app is first launch it will fetch the list of schools as well as the list of School's SAT Scores from the respective API. Once the data is fetch it will show to the user a SearchBar and the list of schools. In the event that it was unable to fetch the data a snackbar will be displayed prompting the user to retry making the API calls. Clicking on the Call Button redirects the user to call the School. While clicking on the School Info Button redirects the user to further information on the School.

### Goal
Create a browser based or native app to provide information on NYC High schools.

1. Display a list of NYC High Schools.
1. Get your data here: [https://data.cityofnewyork.us/Education/DOE-High-School-Directory-2017/s3k6-pzi2](https://data.cityofnewyork.us/Education/DOE-High-School-Directory-2017/s3k6-pzi2)
2. Selecting a school should show additional information about the school
1. Display all the SAT scores - include Math, Reading and Writing.
1. SAT data here: [https://data.cityofnewyork.us/Education/SAT-Results/f9bf-2cp4](https://data.cityofnewyork.us/Education/SAT-Results/f9bf-2cp4)
2. It is up to you to decide what additional information to display
