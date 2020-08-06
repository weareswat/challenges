This exercise is from an actual report on ClanHR. The objective is to build
a service that tracks model changes and lists them. The service will receive
two user versions, as json:

```json
// old
{"_id": 1,
 "name": "Bruce Norries",
 "address": {"street": "Some street"}}

// new
{"_id": 1,
 "name": "Bruce Willis",
 "address": {"street": "Nakatomi Plaza"}}
```

Note that these json bags can be big and with several nested objects.

After that, a listing endpoint should be made available that returns a
collection of data in the form:

```json
[{"field": "name", "old": "Bruce Norris", "new": "Bruce Willis"},
 {"field": "address.street", "old": "Some Street", "new": "Nakatomi Plaza"}]
```

Note that the listing should be filtered by start date and end date, and that
we only want a change per user/field on that timespan. So for the given events:

```
March: Name from A to B
March: Name from B to C
June: Name from C to D
```

If we filter for March, we should get only one change:

```json
[{"field": "name", "old": "A", "new": "C"}]
```

### Wrap-up

Create two endpoints, one to register the changes, and another to list
the changes based on a start/end date filter.

* Please implement the diff algorithm without extra libraries
* Please use some sort of database
* Please create a Pull Request with your changes, starting after project bootstrap


Tech stack: please use preferably Ruby / Rails or JAVA, but if you are more versatile in a different tech stack, you may use one of your choice.
