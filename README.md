# Alzura AG Case Study

## Use Cases:

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

username : 106901

password : Mobile2022!Dev
