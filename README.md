# Slack TCL
[![Build Status](https://travis-ci.org/matthis-d/slack-tcl.svg?branch=master)](https://travis-ci.org/matthis-d/slack-tcl)

## Goal
Enable use of `/tcl` in Slack in order to get information about next buses times.

## How to use
At the moment, you can add this functionnality to Slack by adding a "Slash command".
The URL to use is ```https://slack-tcl.herokuapp.com/request```.

In order to do a request type ```/tcl FromStationName:busNumber:DestinationName```.

## Development
This project is at the very beginning. There is still a lot of things to do:
- Store requests to be able to have usage statistics
- Create a webpage to explain all of this
- Search a station list from a name
- Convert wrong spelling on stations names into correct one for the API

## Known issues
At the moment, the `FromStationName` has to be spelled exactly as it is in the TCL database.
It is not possible to get all buses times for a station
