Feature: Validating Place API's
Scenario Outline: Verify if Place is being Succesfully added using AddPlace_API
	Given Add Place Payload with <name> <language> <address>
	When user calls with <method> http request <resource>
	Then the API call got success with status code 200

Examples:
	| name | language |address | method | resource |
	| AAhouse | English | World cross center | POST | /maps/api/place/add/json |
