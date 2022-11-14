# AlzAgCase
Test Case Study

## User Cases:

1. If first run, User sees splash screen for 2 seconds and redirected to Login Screen.
2. If already authenticated before, and user navigated back, Splash screen appears and user redirected to Order Fragment because auth is valid.
3. User types their user and password, presses login, loading bar shows.
4. If User credentials are true, then user sees all available orders list.
5. User sees order list as sorted desc at first runtime.
6. User clicks sort button on the appbar then order list is sorted asc and list goes to the top.
7. User clicks logout button then app removes auth token and screen redirected to Login screen.
8. User clicks an item on the list and the Details fragment shows relevant detail order data.
9. User presses back button on the details fragment and app shows Order Fragment.


## 3rd Party Libraries Used 

1. Hilt : Dependency Injection.
2. Retrofit : get results from the API : http client.
3. Moshi : Json library that parse JSON into Kotlin classes. injected into retrofit.
4. Okhttp: AuthInterceptor http client , injected in the retrofit.


## Architecture Used

- Model View ViewModel (MVVM)


## Implementation Steps

At first, I read the API documentation and tried to understand the auth mechanism, request headers, orders api requests. It took about 2 to 3 hours of work because of my inexperience about auth and tokens, the authentication headers complicated my mind and done a heavy research.

I thought about the architecture I will build, so to not over complicate things I decided to go with simple MVVM architecture with simple Repository class making request to API using Retrofit.
Using LiveData to observe in the activity, at first I would like to use Flow but only implemented Flow in the repository then collect the emitted data in the viewmodel then to pass the data to activity using LiveData and observed the live data in the activities. Deciding the architecture took about 2 hours of work.
Simple architecture is 
Activity/Fragment → ViewModel → Repository → Retrofit → Api

I used MVVM architecture, one viewModel for each fragment and activity (except for OrderDetailsFragment because in this fragment the data directly comes from OrdersFragment)

I would like to mention that I’ve never implemented a basic auth before so I knew the research and implementation of authentication will be hard, first, I followed a path like mocking like I have the auth string so statically typed auth token in retrofit functions to try if I can get the relevant orders data with my auth informations to see if it works. After seeing I can get the data, I immediately get on to create layouts. At first I decided to implement every screens with activities but then changed my mind to implement Order pages as fragments.

During the development of the project, I heavily used my previous projects and project samples because I knew I have very limited time span. I mostly resemble the layouts and main UI code. Used navargs and navigation components to navigate through fragments. 
I also implemented the Navigator component which is a good use case to navigate through activities. Here, ClearBackStack functionality is important because User may back to the previous screens like orders page after logging out etc. Clearing the back stack is a way of preventing the unwanted navigation between screens. 


## App Screens

- Splash Screen (SplashActivity and SplashModelView)
- Login Screen (LoginActivity and LoginViewModel)
- Main Screen (MainActivity : contains OrdersFragment and OrderDetailsFragment)

