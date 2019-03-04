Emulator Client
======
Service for managing connection with Android Virtual Device (AVD) Emulator.

Connect with the address and port from configuration
---------------
Connects the app with AVD running on address and port specified in '*.properties' file.
```
POST /api/v1/emulator_client/connect
```

### Responses
#### Success - 200
Success response returns object containing address and port of the connected AVD.

**Response object schema**

| Parameter | Type   | Description                               |
|:----------|:-------|:------------------------------------------|
| address   | String | Address of running Android Virtual Device |
| port      | Integer| Port of running Android Virtual Device    |

#####Example success response
```
{
    "address": "127.0.0.1",
    "port": 5554
}
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-03T20:04:41.579+0000",
    "status": 404,
    "error": "Not Found",
    "message": "No message available",
    "path": "/api/v1/emulator_client/connect"
}
```

Connect with passed address and port
---------------
Connects the app with AVD running on address and port specified in path.
```
POST /api/v1/emulator_client/connect/{address}/{port}
```

### Path parameters
| Parameter | Type    | Description                               | Constraint |
|:----------|:--------|:------------------------------------------|:-----------|
| address   | String  | Address of running Android Virtual Device | required   |
| port      | Integer | Port of running Android Virtual Device    | required   |

### Responses
#### Success - 200
Success response returns object containing address and port of the connected AVD.

**Response object schema**

| Parameter | Type   | Description                               |
|:----------|:-------|:------------------------------------------|
| address   | String | Address of running Android Virtual Device |
| port      | Integer| Port of running Android Virtual Device    |

##### Example success response
```
{
    "address": "127.0.0.1",
    "port": 5554
}
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:39:48.611+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Could not open/close client connection.",
    "path": "/api/v1/emulator_client/connect/127.0.0.1/5554"
}
```

Disconnect
---------------
Disconnects the app from the AVD.
```
POST /api/v1/emulator_client/disconnect
```

### Responses
#### Success - 200
Success response returns object containing address and port of the disconnected AVD.

**Response object schema**

| Parameter | Type    | Description                               |
|:----------|:--------|:------------------------------------------|
| address   | String  | Address of running Android Virtual Device |
| port      | Integer | Port of running Android Virtual Device    |

##### Example success response
```
{
    "address": "127.0.0.1",
    "port": 5554
}
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:57:01.538+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Could not close client connection.",
    "path": "/api/v1/emulator_client/disconnect"
}
```