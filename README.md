# HWR OOP Lecture Project Arduino motion sensor

This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at HWR Berlin (summer term 2022).

> :warning: This code is for educational purpose only. Do not rely on it!

## Abstract

This programme controls a motion detector that is controlled via an Arduino. Communication is message-based via the serial interface.
The programme is controlled by commands that are entered into the console.
The biggest difficulty was to make the code that controls the serial interface testable.

## Feature List

| Number | Feature | Tests |
|--------|---------|-------|
| 1      | activating the sensor| /     |
| 2      | deactivating the sensor| /     |
| 3      | listening for motion detections| /     |

## Additional Dependencies

| Number | Dependency Name | Dependency Description | Why is it necessary? |
|--------|-----------------|------------------------|----------------------|
| 1      | jSerialComm-2.9.1| Communication via serial interface| communication with Arduino|

