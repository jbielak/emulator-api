Authentication
======
Service for managing authentication to Android Virtual Device (AVD) Emulator.

Authenticate
---------------
Authenticates the app to the AVD with authentication token, that can be found in `.emulator_console_auth_token` file in 
your home directory
```
POST /api/v1/auth
```

### Query parameters
| Parameter | Type   | Description                                                                                                                                                                  | Constraint |
|:----------|:-------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-----------|
| authToken | String | Authentication token of running Android Virtual Device. Auth token will be taken from the `avd.auth.token` property from `*properties` file if not provided as query param.  | optional   |

### Responses
#### Success - 200
Success response returns result of the authentication to the AVD attempt.

**Response schema**

| Parameter  | Type    | Description                                     |
|:-----------|:--------|:------------------------------------------------|
| authResult | boolean | Result of the authentication to the AVD attempt |

##### Example success response
```
true
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:39:48.611+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/auth"
}
```

Authentication status
---------------
Returns the authentication status of the app to the AVD.
```
GET /api/v1/auth
```

### Responses
#### Success - 200
Success response returns the status of the authentication to the AVD.

**Response schema**

| Parameter | Type    | Description                             |
|:----------|:--------|:----------------------------------------|
| status    | boolean | Status of the authentication to the AVD |

##### Example success Response
```
true
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:57:01.538+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/auth"
}
```