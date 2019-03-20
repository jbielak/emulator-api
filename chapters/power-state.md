Power State
======
Service enables controlling the power state reported by the Android Virtual Device (AVD) Emulator to the apps.

Display state
---------------
Displays battery and charger state.
```
POST /api/v1/power/display_state
```

### Responses
#### Success - 200
Success response returns the message concerning power state reported by the AVD to the apps.

#####Example success response
```
AC: online status: Not charging health: Good present: true capacity: 100 OK
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:39:48.611+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/power/display_state"
}
```

Set AC State
---------------
Sets AC charging state to on or off.
```
POST /api/v1/general_commands/ac/{enabled}
```

### Path parameters parameters
| Parameter | Type    | Description     | Constraint |
|:----------|:--------|:----------------|:-----------|
| enabled   | boolean | Charging state. | required   |

### Responses
#### Success - 200
Success response returns the message with the status of saving AVD snapshot.

**Response schema**

| Parameter | Type   | Description                 |
|:----------|:-------|:----------------------------|
| response  | String | Status of setting AC state. |

##### Example success Response
```
OK
```

#### Error - 400 Bad Request
Bad Request error is returned when path param is not boolean type (true/false).

##### Example error responses
```
{
    "timestamp": "2019-03-19T20:54:49.726+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Failed to convert value of type 'java.lang.String' to required type 'boolean'; nested exception is java.lang.IllegalArgumentException: Invalid boolean value [d]",
    "path": "/api/v1/power/ac/d"
}
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:57:01.538+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/power/ac/true
}
```

Set Power Status
---------------
Changes battery status as specified.
```
POST /api/v1/power/status/{power_status}
```

### Path parameters
| Parameter    | Type    | Description                                                                         | Constraint |
|:-------------|:--------|:------------------------------------------------------------------------------------|:-----------|
| power_status | String  | Battery status. Possible values: unknown, charging, discharging, not-charging, full | required   |

### Responses
#### Success - 200
Success response returns the message with the status of setting battery state.

**Response schema**

| Parameter | Type   | Description                             |
|:----------|:-------|:----------------------------------------|
| response  | String | Status of setting battery power status. |

##### Example success Response
```
OK
```

#### Error - 400 Bad Request
Bad Request error is returned when incorrect battery status value is passed in path param.

##### Example error responses
```
Invalid power status value. Allowed values: [unknown, charging, discharging, not-charging, full]
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:57:01.538+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/power/status/not-charging
}
```

Set Present State
---------------
Sets battery presence state.
```
POST /api/v1/power/present_state/{present_state_value}
```

### Path parameters
| Parameter           | Type    | Description                          | Constraint |
|:--------------------|:--------|:-------------------------------------|:-----------|
| present_state_value | boolean | Battery presence state (true/false). | required   |

### Responses
#### Success - 200
Success response returns the message with the status of setting battery presence state.

**Response schema**

| Parameter | Type   | Description                               |
|:----------|:-------|:------------------------------------------|
| response  | String | Status of setting battery presence state. |

##### Example success Response
```
OK
```

#### Error - 400 Bad Request
Bad Request error is returned when incorrect battery presence state value is passed in path param.

##### Example error responses
```
{
    "timestamp": "2019-03-19T21:08:32.079+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Failed to convert value of type 'java.lang.String' to required type 'boolean'; nested exception is java.lang.IllegalArgumentException: Invalid boolean value [falsed]",
    "path": "/api/v1/power/present_state/falsed"
}
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:57:01.538+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/power/present_state/true
}
```

Set Health State
---------------
Sets battery health state.
```
POST /api/v1/power/health_state/{health_state_value}
```
   
### Path parameters
| Parameter          | Type    | Description                                                                                      | Constraint |
|:-------------------|:--------|:-------------------------------------------------------------------------------------------------|:-----------|
| health_state_value | String  | Battery health state value. Possible values: unknown, good, overheat, dead, overvoltage, failure | required   |

### Responses
#### Success - 200
Success response returns the message with the status of setting battery health state.

**Response schema**

| Parameter | Type   | Description                             |
|:----------|:-------|:----------------------------------------|
| response  | String | Status of setting battery health state. |

##### Example success Response
```
OK
```

#### Error - 400 Bad Request
Bad Request error is returned when incorrect battery health state value is passed in path param.

##### Example error responses
```
Invalid health state value. Allowed values: [unknown, good, overheat, dead, overvoltage, failure]
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:57:01.538+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/power/health_state/good
}
```

Set Capacity
---------------
Set remaining battery capacity state as a percent from 0 to 100.
```
POST /api/v1/power/capacity/{percent}
```

### Path parameters
| Parameter | Type    | Description                                                 | Constraint |
|:----------|:--------|:------------------------------------------------------------|:-----------|
| percent   | Integer | Remaining battery capacity state as a percent from 0 to 100 | required   |

### Responses
#### Success - 200
Success response returns the message with the status of setting remaining battery capacity state.

**Response schema**

| Parameter | Type   | Description                                         |
|:----------|:-------|:----------------------------------------------------|
| response  | String | Status of setting remaining battery capacity state. |

##### Example success Response
```
OK
```

#### Error - 400 Bad Request
Bad Request error is returned when incorrect percent value (out of range 0 - 100) is passed in path param.

##### Example error responses
```
Invalid capacity value.Allowed values from 0 to 100
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:57:01.538+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/power/capacity/75
}
```