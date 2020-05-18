### Destroy Face

```json
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "action": {
      "type": "string"
    },
    "payload": {
      "type": "object",
      "properties": {
        "localUri": {
          "type": "string"
        },
        "port": {
          "type": "string"
        },
        "remoteUri": {
          "type": "string"
        },
        "scheme": {
          "type": "string"
        }
      },
      "required": [
        "localUri",
        "port",
        "remoteUri",
        "scheme"
      ]
    }
  },
  "required": [
    "action",
    "payload"
  ]
}
```
