Geographic Location
======
Service enables controlling the power state reported by the Android Virtual Device (AVD) Emulator to the apps.

Fix Geo Location
---------------
Sends a simple GPS fix to the emulator
```
POST /api/v1/geo_location/fix/{longitude}/{latitude}
```

### Path parameters
| Parameter | Type   | Description                                    | Constraint |
|:----------|:-------|:-----------------------------------------------|:-----------|
| longitude | double | GPS longitude in range between -180.0 and 180. | required   |
| latitude  | double | GPS latitude in range between -90.0 and 90.    | required   |


### Responses
#### Success - 200
Success response returns the message confirming setting longitude and latitude.

#####Example success response
```
OK
```

#### Error - 400 Bad Request
Bad Request error is returned when longitude or latitude value is out of range or the type is incorrect.

##### Example error responses
```
{
    Invalid latitude value. Allowed values in range: -90.0 - 90.0.
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
    "path": "/api/v1/geo_location/fix/-123.0840/89.00
}
```

Send NMEA
---------------
Sends an NMEA 0183 sentence to the emulated device, as if it were sent from an emulated GPS modem. Start sentence 
with '$GP'. Only '$GPGGA' and '$GPRCM' sentences are currently supported. The following example is a GPGGA (Global Positioning System Fix Data) sentence that gets the time, position, and fix data for a GPS receiver:

`geo nmea $GPGGA ,hhmmss.ss,llll.ll,a,yyyyy.yy,a,x,xx,x.x,x.x,M,x.x,M,x.x,xxxx`

```
POST /api/v1/nmea/{sentence_type}/{sentence}
```

### Path parameters
| Parameter     | Type   | Description                                                                                                                                                                                                                   | Constraint |
|:--------------|:-------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-----------|
| sentence_type | String | Sentence type. Currently supported types: $GPGGA, $GPRCM                                                                                                                                                                      | required   |
| sentence      | String | Sentence in correct format. For $GPGGA: `hhmmss.ss,llll.ll,a,yyyyy.yy,a,x,xx,x.x,x.x,M,x.x,M,x.x,xxxx`. For $GPRMC: `hhmmss.ss,A,llll.ll,a,yyyyy.yy,a,x.x,x.x,ddmmyy,x.x,a*hh`. More examples: http://aprs.gids.nl/nmea/#rmc  | required   |

### Responses
#### Success - 200
Success response returns the message confirming sending NMEA.

**Response schema**

| Parameter | Type   | Description             |
|:----------|:-------|:------------------------|
| response  | String | Status of sending NMEA. |

##### Example success Response
```
OK
```

#### Error - 400 Bad Request
Bad Request error is returned when passed sentence type is not supported or param type is incorrect.

##### Example error responses
```
{
Invalid sentence type value. Allowed values: [$GPRMC, $GPGGA]
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
    "path": "/api/v1/geo_location/nmea/$GPRMC/071236,A,3751.65,S,14527.36,E,000.0,073.0,130309,011.3,E*62
}
```