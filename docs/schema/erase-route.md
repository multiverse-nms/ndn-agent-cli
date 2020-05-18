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
        "prefix": {
          "type": "string"
        },
        "faceid": {
          "type": "integer"
        },
        "origin": {
          "type": "integer"
        }
      },
      "required": [
        "prefix",
        "faceid",
        "origin"
      ]
    }
  },
  "required": [
    "action",
    "payload"
  ]
}
