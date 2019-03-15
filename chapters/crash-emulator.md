Crash Emulator
======
Service for managing crashing Android Virtual Device (AVD) Emulator.

Crash
---------------
Crash the emulator during app execution.
```
POST /api/v1/crash
```

### Responses
#### Success - 200
Success response returns empty string.

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:39:48.611+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/crash"
}
```

Crash On Exit
---------------
Crash the emulator when the app exits.
```
POST /api/v1/crash/on_exit
```

### Responses
#### Success - 200
Success response returns empty string.

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:39:48.611+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/crash/on_exit"
}
```
