General Command
======
Service enables sending general commands to Android Virtual Device (AVD) Emulator.

Rotate
---------------
Rotates AVD screen.
```
POST /api/v1/general_commands/rotate
```

### Responses
#### Success - 200
Success response returns `true` when rotate was successfully invoked.

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:39:48.611+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/general_commands/rotate"
}
```

AVD
---------------
Query, control, and manage the AVD.
```
GET /api/v1/general_commands/avd/{action}
```

### Path parameters
| Parameter | Type    | Description                                                   | Constraint |
|:----------|:--------|:--------------------------------------------------------------|:-----------|
| action    | String  | Action to invoke. Possible actions: stop, start, status, name | required   |

### Actions details
| Action | Description                                                       |
|:-------|:------------------------------------------------------------------|
| stop   | Stop the execution of the device.                                 |
| start  | Start the execution of the device.                                |
| status | Query the virtual device status, which can be running or stopped. |
| name   | Query the virtual device name.                                    |

### Responses
#### Success - 200
Success response returns the message returned by the AVD.

**Response schema**

| Parameter | Type   | Description                                 |
|:----------|:-------|:--------------------------------------------|
| response  | String | Message with the status of performed action |

##### Example success Response
```
virtual device is running OK
```

#### Error - 404 Bad Request
Bad Request error is returned when incorrect action is passed as the parameter.

##### Example error responses
```
{
    "timestamp": "2019-03-04T19:57:01.538+0000",
    "status": 404,
    "error": "Bad Request",
    "message": "Invalid option action. Allowed values: list, save, load, delete",
    "path": "/api/v1/general_commands/avd/read
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
    "path": "/api/v1/general_commands/avd/{action}
}
```

AVD Snapshot List
---------------
List all saved snapshots.
```
GET /api/v1/general_commands/avd_snapshot/list
```

### Responses
#### Success - 200
Success response returns the status of the authentication to the AVD.

**Response schema**

| Parameter | Type   | Description                                                          |
|:----------|:-------|:---------------------------------------------------------------------|
| response  | String | List of saved snapshot or message that there are no saved snapshots. |

##### Example success Response
```
List of snapshots present on all disks:
ID        TAG                 VM SIZE                DATE       VM CLOCK
--        default_boot           8.7M 2019-03-02 23:50:57  118:39:24.183
OK
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:57:01.538+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/general_commands/avd_snapshot/list
}
```

AVD Snapshot Load
---------------
Load the named snapshot.
```
GET /api/v1/general_commands/avd_snapshot
```

### Query parameters
| Parameter | Type    | Description    | Constraint |
|:----------|:--------|:---------------|:-----------|
| name      | String  | Snapshot name. | required   |

### Responses
#### Success - 200
Success response returns the message with the status of loading AVD snapshot.

**Response schema**

| Parameter | Type   | Description                     |
|:----------|:-------|:--------------------------------|
| response  | String | Status of loading AVD snapshot. |

##### Example success Response
```
OK
```

#### Error - 404 Bad Request
Bad Request error is returned when there is no snapshot name.

##### Example error responses
```
{
    "timestamp": "2019-03-04T19:57:01.538+0000",
    "status": 404,
    "error": "Bad Request",
    "message": "Snapshot name is required for action: load",
    "path": "/api/v1/general_commands/avd_snapshot
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
    "path": "/api/v1/general_commands/avd_snapshot?name=test
}
```

AVD Snapshot Save
---------------
Save the snapshot as name.
```
POST /api/v1/general_commands/avd_snapshot
```

### Query parameters
| Parameter | Type    | Description    | Constraint |
|:----------|:--------|:---------------|:-----------|
| name      | String  | Snapshot name. | required   |

### Responses
#### Success - 200
Success response returns the message with the status of saving AVD snapshot.

**Response schema**

| Parameter | Type   | Description                    |
|:----------|:-------|:-------------------------------|
| response  | String | Status of saving AVD snapshot. |

##### Example success Response
```
OK
```

#### Error - 404 Bad Request
Bad Request error is returned when there is no snapshot name.

##### Example error responses
```
{
    "timestamp": "2019-03-04T19:57:01.538+0000",
    "status": 404,
    "error": "Bad Request",
    "message": "Snapshot name is required for action: save",
    "path": "/api/v1/general_commands/avd_snapshot
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
    "path": "/api/v1/general_commands/avd_snapshot?name=test
}
```

AVD Snapshot Delete
---------------
Delete the named snapshot.
```
DELETE /api/v1/general_commands/avd_snapshot
```

### Query parameters
| Parameter | Type    | Description    | Constraint |
|:----------|:--------|:---------------|:-----------|
| name      | String  | Snapshot name. | required   |

### Responses
#### Success - 200
Success response returns the message with the status of deleting AVD snapshot.

**Response schema**

| Parameter | Type   | Description                      |
|:----------|:-------|:---------------------------------|
| response  | String | Status of deleting AVD snapshot. |

##### Example success Response
```
OK
```

#### Error - 404 Bad Request
Bad Request error is returned when there is no snapshot name.

##### Example error responses
```
{
    "timestamp": "2019-03-04T19:57:01.538+0000",
    "status": 404,
    "error": "Bad Request",
    "message": "Snapshot name is required for action: delete",
    "path": "/api/v1/general_commands/avd_snapshot
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
    "path": "/api/v1/general_commands/avd_snapshot?name=test
}
```

Ping
---------------
Check whether the virtual device is running.
```
GET /api/v1/general_commands/ping
```

### Responses
#### Success - 200
Success response returns status of AVD lifecycle.

##### Example success Response
```
I am alive! OK
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:39:48.611+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/general_commands/ping"
}
```

Kill
---------------
Terminate the virtual device.

```
POST /api/v1/general_commands/kill
```

### Responses
#### Success - 200
Success response returns status of terminating AVD.

##### Example success Response
```
OK: killing emulator, bye bye OK
```

#### Error - 4XX, 5XX
##### Example error response
```
{
    "timestamp": "2019-03-04T19:39:48.611+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error message.",
    "path": "/api/v1/general_commands/kill"
}
```